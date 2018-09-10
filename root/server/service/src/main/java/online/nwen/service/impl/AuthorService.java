package online.nwen.service.impl;

import online.nwen.domain.Anthology;
import online.nwen.domain.Author;
import online.nwen.domain.Role;
import online.nwen.repository.IAnthologyRepository;
import online.nwen.repository.IArticleRepository;
import online.nwen.repository.IAuthorRepository;
import online.nwen.service.api.IAuthorService;
import online.nwen.service.api.exception.ServiceException;
import online.nwen.service.dto.author.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceException;
import java.util.Date;
import java.util.Optional;

@Service
class AuthorService implements IAuthorService {
    private static final Logger logger = LoggerFactory
            .getLogger(AuthorService.class);
    private IAuthorRepository authorRepository;
    private IAnthologyRepository anthologyRepository;
    private IArticleRepository articleRepository;
    private PasswordEncoder passwordEncoder;

    AuthorService(
            IAuthorRepository authorRepository,
            IAnthologyRepository anthologyRepository,
            IArticleRepository articleRepository,
            PasswordEncoder passwordEncoder) {
        this.authorRepository = authorRepository;
        this.anthologyRepository = anthologyRepository;
        this.articleRepository = articleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public RegisterAuthorResultDTO register(RegisterAuthorDTO authorRegisterDTO)
            throws ServiceException {
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
        author.setRegisterDate(new Date());
        author.getRoles().add(Role.AUTHOR);
        this.authorRepository.save(author);
        Anthology anthology = new Anthology();
        anthology.setAuthorId(author.getId());
        this.anthologyRepository.save(anthology);
        author.setDefaultAnthologyId(anthology.getId());
        this.authorRepository.save(author);
        RegisterAuthorResultDTO result = new RegisterAuthorResultDTO();
        result.setAuthorId(author.getId());
        return result;
    }

    @Override
    public AuthorDetailDTO findDetailById(String id) throws ServiceException {
        Optional<Author> authorOptional = this.authorRepository.findById(id);
        if (authorOptional.isPresent()) {
            return this.convert(authorOptional.get());
        }
        throw new ServiceException(ServiceException.Code.AUTHOR_NOT_EXIST_ERROR);
    }

    private AuthorDetailDTO convert(Author author) {
        AuthorDetailDTO result = new AuthorDetailDTO();
        result.setAuthorId(author.getId());
        result.setNickName(author.getNickName());
        result.setLastLoginDate(author.getLastLoginDate());
        result.setRegisterDate(author.getRegisterDate());
        result.setToken(author.getToken());
        author.getRoles().forEach(role -> {
            result.getRoles().add(role);
        });
        author.getTags().forEach(authorTag -> {
            result.getTags().add(authorTag);
        });
        result.setAnthologyNumber(
                this.anthologyRepository.countByAuthorId(author.getId()));
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
