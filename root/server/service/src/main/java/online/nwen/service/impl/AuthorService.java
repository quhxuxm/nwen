package online.nwen.service.impl;

import online.nwen.domain.*;
import online.nwen.repository.*;
import online.nwen.service.api.IAuthorService;
import online.nwen.service.api.exception.ServiceException;
import online.nwen.service.dto.author.*;
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
    public RegisterAuthorResultDTO register(RegisterAuthorDTO authorRegisterDTO)
            throws ServiceException {
        try {
            if (this.authorRepository
                    .existsByToken(authorRegisterDTO.getToken())) {
                logger.error("Can not register because of token exist already, "
                        + "token = {}.", authorRegisterDTO.getToken());
                throw
                        new ServiceException(
                                ServiceException.Code.REGISTER_TOKEN_EXIST_ERROR);
            }
            if (this.authorRepository
                    .existsByNickName(authorRegisterDTO.getNickname())) {
                logger.error(
                        "Can not register because of nick name exist already, "
                                + "nick name = {}.",
                        authorRegisterDTO.getNickname());
                throw
                        new ServiceException(
                                ServiceException.Code.REGISTER_NICKNAME_EXIST_ERROR);
            }
            Author author = new Author();
            author.setToken(authorRegisterDTO.getToken());
            author.setPassword(this.passwordEncoder
                    .encode(authorRegisterDTO.getPassword()));
            author.setNickName(authorRegisterDTO.getNickname());
            Role authorRole = this.roleRepository
                    .findByName(Role.Name.ROLE_AUTHOR);
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
            RegisterAuthorResultDTO result = new RegisterAuthorResultDTO();
            result.setAuthorId(author.getId());
            return result;
        } catch (PersistenceException e) {
            logger.error(
                    "Fail to register because of exception when save author "
                            + "default anthology.", e);
            throw new ServiceException(
                    "Fail to register because of exception when save author "
                            + "default anthology.",
                    ServiceException.Code.SYSTEM_ERROR);
        }
    }

    @Transactional
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
                    ServiceException.Code.AUTHOR_NOT_EXIST_ERROR);
        } catch (PersistenceException e) {
            logger.error(
                    "Can not find author detail because of the exception..", e);
            throw new ServiceException(
                    "Can not find author detail because of the exception..",
                    ServiceException.Code.SYSTEM_ERROR);
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
                            authorTag.setWeight(1L);
                            this.authorTagRepository.save(authorTag);
                        });
            });
        } catch (PersistenceException e) {
            logger.error("Can not assign tog to author because of exception.",
                    e);
            throw new ServiceException(
                    "Can not assign tog to author because of exception.", e,
                    ServiceException.Code.SYSTEM_ERROR);
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
                    ServiceException.Code.SYSTEM_ERROR);
        }
    }
}
