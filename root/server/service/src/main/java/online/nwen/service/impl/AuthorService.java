package online.nwen.service.impl;

import online.nwen.domain.*;
import online.nwen.repository.*;
import online.nwen.service.api.IAuthorService;
import online.nwen.service.api.exception.ServiceException;
import online.nwen.service.dto.author.*;
import online.nwen.service.impl.common.ICommonConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
class AuthorService implements IAuthorService {
    private static final Logger logger = LoggerFactory
            .getLogger(AuthorService.class);
    private IAuthorDefaultAnthologyRepository authorDefaultAnthologyRepository;
    private IAuthorRepository authorRepository;
    private IRoleRepository roleRepository;
    private IAnthologyRepository anthologyRepository;
    private IAuthorTagRepository authorTagRepository;
    private IArticleRepository articleRepository;
    private ITagRepository tagRepository;
    private IAuthorFollowerRepository authorFollowerRepository;
    private IArticleCommentRepository articleCommentRepository;
    private PasswordEncoder passwordEncoder;

    AuthorService(
            IAuthorDefaultAnthologyRepository authorDefaultAnthologyRepository,
            IAuthorRepository authorRepository, IRoleRepository roleRepository,
            IAnthologyRepository anthologyRepository,
            IAuthorTagRepository authorTagRepository,
            IArticleRepository articleRepository, ITagRepository tagRepository,
            IAuthorFollowerRepository authorFollowerRepository,
            IArticleCommentRepository articleCommentRepository,
            PasswordEncoder passwordEncoder) {
        this.authorDefaultAnthologyRepository = authorDefaultAnthologyRepository;
        this.authorRepository = authorRepository;
        this.roleRepository = roleRepository;
        this.anthologyRepository = anthologyRepository;
        this.authorTagRepository = authorTagRepository;
        this.articleRepository = articleRepository;
        this.tagRepository = tagRepository;
        this.authorFollowerRepository = authorFollowerRepository;
        this.articleCommentRepository = articleCommentRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional(rollbackFor = ServiceException.class)
    @Override
    public Long register(CreateAuthorDTO authorRegisterDTO)
            throws ServiceException {
        try {
            if (this.authorRepository
                    .existsByToken(authorRegisterDTO.getToken())) {
                logger.error("Can not register because of token exist already, "
                        + "token = {}.", authorRegisterDTO.getToken());
                throw
                        new ServiceException(
                                ServiceException.Code.AUTHOR_TOKEN_EXIST);
            }
            if (this.authorRepository
                    .existsByNickName(authorRegisterDTO.getNickName())) {
                logger.error(
                        "Can not register because of nick name exist already, "
                                + "nick name = {}.",
                        authorRegisterDTO.getNickName());
                throw
                        new ServiceException(
                                ServiceException.Code.AUTHOR_NICK_NAME_EXIST);
            }
            Author author = new Author();
            author.setToken(authorRegisterDTO.getToken());
            author.setPassword(this.passwordEncoder
                    .encode(authorRegisterDTO.getPassword()));
            author.setNickName(authorRegisterDTO.getNickName());
            Role authorRole = this.roleRepository
                    .findByName(ICommonConstant.RoleName.AUTHOR);
            Set<Role> roleSet = new HashSet<>();
            roleSet.add(authorRole);
            author.setRoles(roleSet);
            author.setRegisterDate(new Date());
            this.authorRepository.save(author);
            Anthology anthology = new Anthology();
            anthology.setAuthor(author);
            this.anthologyRepository.save(anthology);
            AuthorDefaultAnthology authorDefaultAnthology = new AuthorDefaultAnthology();
            AuthorDefaultAnthology.PK authorDefaultAnthologyPK = new AuthorDefaultAnthology.PK();
            authorDefaultAnthologyPK.setAnthology(anthology);
            authorDefaultAnthologyPK.setAuthor(author);
            authorDefaultAnthology.setPk(authorDefaultAnthologyPK);
            this.authorDefaultAnthologyRepository.save(authorDefaultAnthology);
            return author.getId();
        } catch (PersistenceException e) {
            logger.error(
                    "Fail to register because of exception when save author "
                            + "default anthology.", e);
            throw new ServiceException(
                    "Fail to register because of exception when save author "
                            + "default anthology.",
                    ServiceException.Code.PERSISTENCE_FAIL);
        }
    }

    @Override
    public AuthorDetailDTO findDetailById(Long id) throws ServiceException {
        try {
            Author author = this.authorRepository.getOne(id);
            return this.convert(author);
        } catch (EntityNotFoundException e) {
            logger.error("Can not find author detail because of the exception.",
                    e);
            throw new ServiceException(
                    "Can not find author detail because of the exception.",
                    ServiceException.Code.AUTHOR_NOT_EXIST_BY_ID);
        } catch (PersistenceException e) {
            logger.error(
                    "Can not find author detail because of the exception..", e);
            throw new ServiceException(
                    "Can not find author detail because of the exception..",
                    ServiceException.Code.PERSISTENCE_FAIL);
        }
    }

    @Override
    public AuthorDetailDTO loginByToken(String token) throws ServiceException {
        try {
            Author author = this.authorRepository.findByToken(token);
            if (author == null) {
                throw new ServiceException(
                        "Can not find author detail because of the exception.",
                        ServiceException.Code.AUTHOR_NOT_EXIST_BY_TOKEN);
            }
            AuthorDetailDTO result = this.convert(author);
            author.setLastLoginDate(new Date());
            this.authorRepository.save(author);
            return result;
        } catch (PersistenceException e) {
            logger.error("Can not login because of the exception.", e);
            throw new ServiceException(
                    "Can not login because of the exception.",
                    ServiceException.Code.PERSISTENCE_FAIL);
        }
    }

    private AuthorDetailDTO convert(Author author) {
        AuthorDetailDTO result = new AuthorDetailDTO();
        result.setAuthorId(author.getId());
        result.setNickName(author.getNickName());
        result.setLastLoginDate(author.getLastLoginDate());
        result.setRegisterDate(author.getRegisterDate());
        result.setToken(author.getToken());
        author.getRoles().forEach(role -> {
            result.getRoles().add(role.getName());
        });
        Set<AuthorTag> authorTags = this.authorTagRepository
                .findAllByPkAuthorAndIsSelectedIsTrue(author);
        authorTags.forEach(authorTag -> {
            result.getTags().add(authorTag.getPk().getTag().getText());
        });
        result.setAnthologyNumber(
                this.anthologyRepository.countByAuthor(author));
        result.setArticleNumber(
                this.articleRepository.countByAnthologyAuthor(author));
        result.setCommentNumber(
                this.articleCommentRepository.countByAuthor(author));
        result.setFollowedByNumber(
                this.authorFollowerRepository.countByPkAuthor(author));
        AuthorDefaultAnthology authorDefaultAnthology = this.authorDefaultAnthologyRepository
                .findByPkAuthor(author);
        result.setDefaultAnthologyId(
                authorDefaultAnthology.getPk().getAnthology().getId());
        return result;
    }

    @Transactional
    @Override
    public void assignTagsToAuthor(AuthorAssignTagsDTO authorAssignTagsDTO)
            throws ServiceException {
        try {
            Author authorFromDb = this.authorRepository
                    .getOne(authorAssignTagsDTO.getAuthorId());
            authorAssignTagsDTO.getTags().forEach(tagText -> {
                Tag tag = this.tagRepository.findByText(tagText);
                if (tag == null) {
                    Tag newTag = new Tag();
                    newTag.setText(tagText);
                    this.tagRepository.save(newTag);
                    tag = newTag;
                }
                AuthorTag.PK authorTagPk = new AuthorTag.PK();
                authorTagPk.setAuthor(authorFromDb);
                authorTagPk.setTag(tag);
                this.authorTagRepository.findById(authorTagPk)
                        .ifPresentOrElse(authorTag -> {
                            if (!authorAssignTagsDTO.isSelect()) {
                                logger.debug(
                                        "Add weight to the author tag when it is not selected directly.");
                                authorTag.setWeight(authorTag.getWeight() + 1);
                                this.authorTagRepository.save(authorTag);
                                return;
                            }
                            logger.debug(
                                    "Will not create new author tag because "
                                            + "it exist already.");
                        }, () -> {
                            AuthorTag authorTag = new AuthorTag();
                            authorTag.setPk(authorTagPk);
                            authorTag.setSelected(true);
                            authorTag.setWeight(
                                    ICommonConstant.DefaultValue.AUTHOR_SELECTED_TAG_INIT_WEIGHT);
                            this.authorTagRepository.save(authorTag);
                        });
            });
        } catch (PersistenceException e) {
            logger.error("Can not assign tog to author because of exception.",
                    e);
            throw new ServiceException(
                    "Can not assign tog to author because of exception.", e,
                    ServiceException.Code.PERSISTENCE_FAIL);
        }
    }

    @Override
    public void assignFollowerToAuthor(
            AuthorAssignFollowerDTO authorAssignFollowerDTO)
            throws ServiceException {
        try {
            Author author = this.authorRepository
                    .getOne(authorAssignFollowerDTO.getAuthorId());
            Author follower = this.authorRepository
                    .getOne(authorAssignFollowerDTO.getFollowerId());
            AuthorFollower.PK authorFollowerPk = new AuthorFollower.PK();
            authorFollowerPk.setAuthor(author);
            authorFollowerPk.setFollower(follower);
            this.authorFollowerRepository.findById(authorFollowerPk)
                    .ifPresentOrElse(authorFollower -> {
                        logger.debug(
                                "Will not create new author follower because it "
                                        + "exist" + " already.");
                    }, () -> {
                        AuthorFollower authorFollower = new AuthorFollower();
                        authorFollower.setPk(authorFollowerPk);
                        authorFollower.setFollowDate(new Date());
                        this.authorFollowerRepository.save(authorFollower);
                        author.getAdditionalInfo().setFollowerNumber(
                                this.authorFollowerRepository
                                        .countByPkAuthor(author));
                    });
        } catch (PersistenceException e) {
            logger.error("Can not assign tog to author because of exception.",
                    e);
            throw new ServiceException(
                    "Can not assign tog to author because of exception.", e,
                    ServiceException.Code.PERSISTENCE_FAIL);
        }
    }

    @Override
    public AuthorAuthenticateDTO findForAuthenticate(String token)
            throws ServiceException {
        try {
            Author author = this.authorRepository.findByToken(token);
            if (author == null) {
                throw new ServiceException(
                        ServiceException.Code.AUTHOR_NOT_EXIST_BY_TOKEN);
            }
            AuthorAuthenticateDTO result = new AuthorAuthenticateDTO();
            result.setId(author.getId());
            result.setToken(author.getToken());
            result.setPassword(author.getPassword());
            result.setRoles(author.getRoles().stream().map(Role::getName)
                    .collect(Collectors.toSet()));
            return result;
        } catch (PersistenceException e) {
            throw new ServiceException(e,
                    ServiceException.Code.PERSISTENCE_FAIL);
        }
    }
}
