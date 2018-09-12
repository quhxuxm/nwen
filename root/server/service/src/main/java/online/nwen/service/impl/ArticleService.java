package online.nwen.service.impl;

import online.nwen.domain.Anthology;
import online.nwen.domain.Article;
import online.nwen.domain.Author;
import online.nwen.repository.IAnthologyRepository;
import online.nwen.repository.IArticleRepository;
import online.nwen.repository.IAuthorRepository;
import online.nwen.service.api.IArticleService;
import online.nwen.service.api.IContentService;
import online.nwen.service.api.exception.ServiceException;
import online.nwen.service.dto.article.SaveArticleDTO;
import online.nwen.service.dto.article.SaveArticleResultDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
class ArticleService implements IArticleService {
    private static final Logger logger = LoggerFactory
            .getLogger(ArticleService.class);
    private IArticleRepository articleRepository;
    private IAnthologyRepository anthologyRepository;
    private IAuthorRepository authorRepository;
    private IContentService contentService;

    ArticleService(
            IArticleRepository articleRepository,
            IAnthologyRepository anthologyRepository, IAuthorRepository authorRepository,
            IContentService contentService) {
        this.articleRepository = articleRepository;
        this.anthologyRepository = anthologyRepository;
        this.authorRepository = authorRepository;
        this.contentService = contentService;
    }

    @Override
    public SaveArticleResultDTO saveArticle(SaveArticleDTO saveArticleDTO) {
        if (saveArticleDTO.getAuthorId() == null) {
            logger.error("Author id is empty.", saveArticleDTO.getAuthorId());
            throw new ServiceException(ServiceException.Code.INPUT_ERROR_EMPTY_AUTHOR_ID);
        }
        Optional<Author> authorOptional = this.authorRepository.findById(saveArticleDTO.getAuthorId());
        if (!authorOptional.isPresent()) {
            logger.error("Author not exist with id, author id = {}", saveArticleDTO.getAuthorId());
            throw new ServiceException(ServiceException.Code.AUTHOR_ERROR_NOT_EXIST);
        }
        if (saveArticleDTO.getArticleId() != null) {
            logger.debug("Article id assigned, update the article, article id = {}", saveArticleDTO.getArticleId());
            return this.updateArticle(saveArticleDTO);
        }
        logger.debug("No article id assigned, create the article.");
        return this.createArticle(saveArticleDTO);
    }

    private SaveArticleResultDTO updateArticle(SaveArticleDTO saveArticleDTO) {
        Optional<Article> articleOptional = this.articleRepository.findById(saveArticleDTO.getArticleId());
        if (!articleOptional.isPresent()) {
            logger.error("The article with id {} is not exist.", saveArticleDTO.getArticleId());
            throw new ServiceException(ServiceException.Code.ARTICLE_ERROR_NOT_EXIST);
        }
        Article article = articleOptional.get();
        if (!article.getAuthorId().equals(saveArticleDTO.getAuthorId())) {
            logger.error("The author is not the owner of article, author id = {}, article id = {}.",
                    saveArticleDTO.getAuthorId(), saveArticleDTO.getArticleId());
            throw new ServiceException(ServiceException.Code.ARTICLE_ERROR_AUTHOR_NOT_OWNER);
        }
        if (!article.getAnthologyId().equals(saveArticleDTO.getAnthologyId())) {
            logger.debug(
                    "The anthology id changed, author maybe want to change the anthology of the article, article id = {} , old anthology id = {}, new anthology id = {}",
                    article.getId(), article.getAnthologyId(), saveArticleDTO.getAnthologyId());
            Optional<Anthology> targetAnthologyOptional =
                    this.anthologyRepository.findById(saveArticleDTO.getAnthologyId());
            if (!targetAnthologyOptional.isPresent()) {
                logger.error("The target anthology is not exist on update article, anthology id = {}.",
                        saveArticleDTO.getAnthologyId());
                throw new ServiceException(ServiceException.Code.ANTHOLOGY_ERROR_NOT_EXIST);
            }
            boolean isOwner = targetAnthologyOptional.get().getAuthorId().equals(saveArticleDTO.getAuthorId());
            boolean isParticipant =
                    targetAnthologyOptional.get().getParticipantAuthorIds().contains(saveArticleDTO.getAuthorId());
            if (!isOwner && !isParticipant) {
                logger.error(
                        "The author is not a owner or participant of the target anthology, author id = {}, anthology id = {}.",
                        saveArticleDTO.getAuthorId(), saveArticleDTO.getAnthologyId());
                throw new ServiceException(ServiceException.Code.ANTHOLOGY_ERROR_AUTHOR_NEITHER_OWNER_NOR_PARTICIPANT);
            }
        }
        article.setTitle(saveArticleDTO.getTitle());
        article.setSummary(saveArticleDTO.getSummary());
        article.setContent(this.parseArticleContent(saveArticleDTO.getContent()));
        article.setUpdateDate(new Date());
        article.setTags(saveArticleDTO.getTags());
        this.articleRepository.save(article);
        SaveArticleResultDTO result = new SaveArticleResultDTO();
        result.setArticleId(article.getId());
        result.setAnthologyId(saveArticleDTO.getAnthologyId());
        return result;
    }

    private SaveArticleResultDTO createArticle(SaveArticleDTO saveArticleDTO) {
        if (saveArticleDTO.getAnthologyId() == null) {
            logger.error("The anthology id is empty on create article.");
            throw new ServiceException(ServiceException.Code.INPUT_ERROR_EMPTY_ANTHOLOGY_ID);
        }
        Optional<Anthology> targetAnthologyOptional =
                this.anthologyRepository.findById(saveArticleDTO.getAnthologyId());
        if (!targetAnthologyOptional.isPresent()) {
            logger.error("The anthology is not exist on create article, anthology id = {}.",
                    saveArticleDTO.getAnthologyId());
            throw new ServiceException(ServiceException.Code.ANTHOLOGY_ERROR_NOT_EXIST);
        }
        boolean isOwner = targetAnthologyOptional.get().getAuthorId().equals(saveArticleDTO.getAuthorId());
        boolean isParticipant =
                targetAnthologyOptional.get().getParticipantAuthorIds().contains(saveArticleDTO.getAuthorId());
        if (!isOwner && !isParticipant) {
            logger.error(
                    "The author is not a owner or participant of the anthology, author id = {}, anthology id = {}.",
                    saveArticleDTO.getAuthorId(), saveArticleDTO.getAnthologyId());
            throw new ServiceException(ServiceException.Code.ANTHOLOGY_ERROR_AUTHOR_NEITHER_OWNER_NOR_PARTICIPANT);
        }
        Article article = new Article();
        article.setTitle(saveArticleDTO.getTitle());
        article.setCreateDate(new Date());
        article.setSummary(saveArticleDTO.getSummary());
        article.setContent(this.parseArticleContent(saveArticleDTO.getContent()));
        article.setUpdateDate(new Date());
        article.setAnthologyId(saveArticleDTO.getAnthologyId());
        article.setAuthorId(saveArticleDTO.getAuthorId());
        this.articleRepository.save(article);
        SaveArticleResultDTO result = new SaveArticleResultDTO();
        result.setArticleId(article.getId());
        result.setAnthologyId(saveArticleDTO.getAnthologyId());
        return result;
    }

    private String parseArticleContent(String content) {
        return this.contentService.parse(content);
    }
}
