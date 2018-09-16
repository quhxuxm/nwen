package online.nwen.service.impl;

import online.nwen.domain.Anthology;
import online.nwen.domain.Author;
import online.nwen.domain.Role;
import online.nwen.repository.IAnthologyRepository;
import online.nwen.repository.IAuthorRepository;
import online.nwen.service.api.IAuthorService;
import online.nwen.service.api.exception.ExceptionCode;
import online.nwen.service.api.exception.ServiceException;
import online.nwen.service.dto.author.GetAuthorDetailDTO;
import online.nwen.service.dto.author.GetAuthorDetailResultDTO;
import online.nwen.service.dto.author.RegisterAuthorDTO;
import online.nwen.service.dto.author.RegisterAuthorResultDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.Optional;

@Service
class AuthorService implements IAuthorService {
    private static final Logger logger = LoggerFactory
            .getLogger(AuthorService.class);
    private IAuthorRepository authorRepository;
    private IAnthologyRepository anthologyRepository;
    private PasswordEncoder passwordEncoder;

    AuthorService(
            IAuthorRepository authorRepository,
            IAnthologyRepository anthologyRepository,
            PasswordEncoder passwordEncoder) {
        this.authorRepository = authorRepository;
        this.anthologyRepository = anthologyRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public RegisterAuthorResultDTO register(RegisterAuthorDTO authorRegisterDTO) {
        if (StringUtils.isEmpty(authorRegisterDTO.getUsername())) {
            logger.error("Can not register because of username is empty.");
            throw new ServiceException(ExceptionCode.INPUT_ERROR_REGISTER_USERNAME_IS_EMPTY);
        }
        if (StringUtils.isEmpty(authorRegisterDTO.getNickname())) {
            logger.error("Can not register because of nickname is empty.");
            throw new ServiceException(ExceptionCode.INPUT_ERROR_REGISTER_NICKNAME_IS_EMPTY);
        }
        if (StringUtils.isEmpty(authorRegisterDTO.getPassword())) {
            logger.error("Can not register because of password is empty.");
            throw new ServiceException(ExceptionCode.INPUT_ERROR_REGISTER_PASSWORD_IS_EMPTY);
        }
        if (this.authorRepository
                .existsByUsername(authorRegisterDTO.getUsername())) {
            logger.error("Can not register because of token exist already, "
                    + "token = {}.", authorRegisterDTO.getUsername());
            throw
                    new ServiceException(
                            ExceptionCode.REGISTER_ERROR_USERNAME_EXIST);
        }
        if (this.authorRepository
                .existsByNickname(authorRegisterDTO.getNickname())) {
            logger.error(
                    "Can not register because of nick name exist already, "
                            + "nick name = {}.",
                    authorRegisterDTO.getNickname());
            throw
                    new ServiceException(
                            ExceptionCode.REGISTER_ERROR_NICKNAME_EXIST);
        }
        Author author = new Author();
        author.setUsername(authorRegisterDTO.getUsername());
        author.setPassword(this.passwordEncoder
                .encode(authorRegisterDTO.getPassword()));
        author.setNickname(authorRegisterDTO.getNickname());
        author.setRegisterDate(new Date());
        author.getRoles().add(Role.AUTHOR);
        author.setTags(authorRegisterDTO.getTags());
        this.authorRepository.save(author);
        Anthology anthology = new Anthology();
        anthology.setAuthorId(author.getId());
        this.anthologyRepository.save(anthology);
        author.setDefaultAnthologyId(anthology.getId());
        author.setAnthologyNumber(author.getAnthologyNumber() + 1);
        this.authorRepository.save(author);
        RegisterAuthorResultDTO result = new RegisterAuthorResultDTO();
        result.setAuthorId(author.getId());
        return result;
    }

    @Cacheable(cacheNames = "author-details", key = "#getAuthorDetailDTO.authorId")
    @Override
    public GetAuthorDetailResultDTO getAuthorDetail(GetAuthorDetailDTO getAuthorDetailDTO) throws ServiceException {
        if (getAuthorDetailDTO.getAuthorId() == null) {
            throw new ServiceException(ExceptionCode.INPUT_ERROR_EMPTY_AUTHOR_ID);
        }
        Optional<Author> authorOptional = this.authorRepository.findById(getAuthorDetailDTO.getAuthorId());
        if (authorOptional.isPresent()) {
            return this.convert(authorOptional.get());
        }
        throw new ServiceException(ExceptionCode.AUTHOR_ERROR_NOT_EXIST);
    }

    private GetAuthorDetailResultDTO convert(Author author) {
        GetAuthorDetailResultDTO result = new GetAuthorDetailResultDTO();
        result.setAuthorId(author.getId());
        result.setNickName(author.getUsername());
        result.setLastLoginDate(author.getLastLoginDate());
        result.setRegisterDate(author.getRegisterDate());
        result.setToken(author.getUsername());
        author.getRoles().forEach(role -> {
            result.getRoles().add(role);
        });
        author.getTags().forEach(authorTag -> {
            result.getTags().add(authorTag);
        });
        result.setAnthologyNumber(author.getAnthologyNumber());
        result.setArticleNumber(
                author.getArticleNumber());
        result.setCommentNumber(
                author.getCommentNumber());
        result.setFollowerNumber(author.getFollowerNumber());
        result.setDefaultAnthologyId(author.getDefaultAnthologyId());
        return result;
    }
}
