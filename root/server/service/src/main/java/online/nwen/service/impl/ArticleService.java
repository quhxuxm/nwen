package online.nwen.service.impl;

import online.nwen.domain.Anthology;
import online.nwen.domain.Article;
import online.nwen.domain.Author;
import online.nwen.repository.IAnthologyRepository;
import online.nwen.repository.IArticleRepository;
import online.nwen.repository.IAuthorRepository;
import online.nwen.service.api.IArticleService;
import online.nwen.service.api.IContentService;
import online.nwen.service.api.exception.ExceptionCode;
import online.nwen.service.api.exception.ServiceException;
import online.nwen.service.configuration.ServiceConfiguration;
import online.nwen.service.dto.article.*;
import online.nwen.service.dto.content.ContentParseResultDTO;
import online.nwen.service.security.SecurityContextHolder;
import online.nwen.service.security.annotation.PrepareSecurityContext;
import online.nwen.service.security.annotation.Security;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.Optional;

@Service
class ArticleService implements IArticleService {
    private static final Logger logger = LoggerFactory
            .getLogger(ArticleService.class);
    private IArticleRepository articleRepository;
    private IAnthologyRepository anthologyRepository;
    private IContentService contentService;
    private ServiceConfiguration serviceConfiguration;
    private IAuthorRepository authorRepository;

    ArticleService(
            IArticleRepository articleRepository,
            IAnthologyRepository anthologyRepository,
            IContentService contentService, ServiceConfiguration serviceConfiguration,
            IAuthorRepository authorRepository) {
        this.articleRepository = articleRepository;
        this.anthologyRepository = anthologyRepository;
        this.contentService = contentService;
        this.serviceConfiguration = serviceConfiguration;
        this.authorRepository = authorRepository;
    }

    @PrepareSecurityContext
    @Override
    public ArticleSummaryDTO getArticleSummary(GetArticleSummaryDTO getArticleSummaryDTO) {
        Author currentAuthor = SecurityContextHolder.INSTANCE.getContext().getAuthor();
        if (getArticleSummaryDTO.getArticleId() == null) {
            logger.error("The article id in request is empty.");
            throw new ServiceException(ExceptionCode.INPUT_ERROR_EMPTY_ARTICLE_ID);
        }
        Optional<Article> articleOptional = this.articleRepository.findById(getArticleSummaryDTO.getArticleId());
        if (!articleOptional.isPresent()) {
            logger.error("The article not exist.");
            throw new ServiceException(ExceptionCode.ARTICLE_ERROR_NOT_EXIST);
        }
        Article article = articleOptional.get();
        Optional<Author> articleAuthorOptional = this.authorRepository.findById(article.getAuthorId());
        if (!articleAuthorOptional.isPresent()) {
            logger.error("The article author not exist.");
            throw new ServiceException(ExceptionCode.SYSTEM_ERROR);
        }
        Author articleAuthor = articleAuthorOptional.get();
        Optional<Anthology> anthologyOptional = this.anthologyRepository.findById(article.getAnthologyId());
        if (!anthologyOptional.isPresent()) {
            logger.error("The article anthology not exist.");
            throw new ServiceException(ExceptionCode.SYSTEM_ERROR);
        }
        Anthology anthology = anthologyOptional.get();
        ArticleSummaryDTO resultDTO = new ArticleSummaryDTO();
        resultDTO.setArticleId(article.getId());
        resultDTO.setAnthologyId(article.getAnthologyId());
        resultDTO.setTitle(article.getTitle());
        resultDTO.setAuthorId(article.getAuthorId());
        resultDTO.setAuthorNickName(articleAuthor.getNickname());
        resultDTO.setAnthologyTitle(anthology.getTitle());
        resultDTO.setSummary(article.getSummary());
        resultDTO.setCreateDate(article.getCreateDate());
        resultDTO.setAnthologyCoverImageId(anthology.getCoverImageId());
        resultDTO.setAuthorIconImageId(articleAuthor.getIconImageId());
        resultDTO.setSummary(article.getSummary());
        resultDTO.setPraiseNumber(article.getPraisesNumber());
        resultDTO.setCommentNumber(article.getCommentNumber());
        resultDTO.setBookmarkNumber(article.getBookmarksNumber());
        resultDTO.setUpdateDate(article.getUpdateDate());
        resultDTO.setViewNumber(article.getViewersNumber());
        return resultDTO;
    }

    @PrepareSecurityContext
    @Override
    public ArticleDetailDTO getArticleDetail(GetArticleDetailDTO getArticleDetailDTO) {
        Author currentAuthor = SecurityContextHolder.INSTANCE.getContext().getAuthor();
        if (getArticleDetailDTO.getArticleId() == null) {
            logger.error("The article id in request is empty.");
            throw new ServiceException(ExceptionCode.INPUT_ERROR_EMPTY_ARTICLE_ID);
        }
        Optional<Article> articleOptional = this.articleRepository.findById(getArticleDetailDTO.getArticleId());
        if (!articleOptional.isPresent()) {
            logger.error("The article not exist.");
            throw new ServiceException(ExceptionCode.ARTICLE_ERROR_NOT_EXIST);
        }
        Article article = articleOptional.get();
        if (article.getViewers().containsKey(currentAuthor.getId())) {
            Date lastViewDate = article.getViewers().get(currentAuthor.getId());
            Date currentDate = new Date();
            long difference = currentDate.getTime() - lastViewDate.getTime();
            if (difference > this.serviceConfiguration.getViewDateInterval()) {
                article.setViewersNumber(article.getViewersNumber() + 1);
                article.getViewers().put(currentAuthor.getId(), new Date());
            }
        } else {
            article.setViewersNumber(article.getViewersNumber() + 1);
            article.getViewers().put(currentAuthor.getId(), new Date());
        }
        this.articleRepository.save(article);
        Optional<Author> articleAuthorOptional = this.authorRepository.findById(article.getAuthorId());
        if (!articleAuthorOptional.isPresent()) {
            logger.error("The article author not exist.");
            throw new ServiceException(ExceptionCode.SYSTEM_ERROR);
        }
        Author articleAuthor = articleAuthorOptional.get();
        Optional<Anthology> anthologyOptional = this.anthologyRepository.findById(article.getAnthologyId());
        if (!anthologyOptional.isPresent()) {
            logger.error("The article anthology not exist.");
            throw new ServiceException(ExceptionCode.SYSTEM_ERROR);
        }
        Anthology anthology = anthologyOptional.get();
        ArticleDetailDTO resultDTO = new ArticleDetailDTO();
        resultDTO.setArticleId(article.getId());
        resultDTO.setAnthologyId(article.getAnthologyId());
        resultDTO.setTitle(article.getTitle());
        resultDTO.setAuthorId(article.getAuthorId());
        resultDTO.setAuthorNickName(articleAuthor.getNickname());
        resultDTO.setAnthologyTitle(anthology.getTitle());
        resultDTO.setContent(article.getContent());
        resultDTO.setCreateDate(article.getCreateDate());
        resultDTO.getTags().addAll(article.getTags());
        resultDTO.setAnthologyCoverImageId(anthology.getCoverImageId());
        resultDTO.setAuthorIconImageId(articleAuthor.getIconImageId());
        resultDTO.setSummary(article.getSummary());
        resultDTO.setPraiseNumber(article.getPraisesNumber());
        resultDTO.setCommentNumber(article.getCommentNumber());
        resultDTO.setBookmarkNumber(article.getBookmarksNumber());
        resultDTO.setUpdateDate(article.getUpdateDate());
        resultDTO.setViewNumber(article.getViewersNumber());
        return resultDTO;
    }

    @Security
    @PrepareSecurityContext
    @Override
    public SaveArticleResultDTO saveArticle(SaveArticleDTO saveArticleDTO) {
        if (StringUtils.isEmpty(saveArticleDTO.getTitle())) {
            throw new ServiceException(ExceptionCode.INPUT_ERROR_EMPTY_ARTICLE_TITLE);
        }
        Author currentAuthor = SecurityContextHolder.INSTANCE.getContext().getAuthor();
        if (saveArticleDTO.getArticleId() != null) {
            logger.debug("Article id assigned, update the article, article id = {}", saveArticleDTO.getArticleId());
            return this.updateArticle(saveArticleDTO, currentAuthor);
        }
        logger.debug("No article id assigned, create the article.");
        return this.createArticle(saveArticleDTO, currentAuthor);
    }

    private SaveArticleResultDTO updateArticle(SaveArticleDTO saveArticleDTO, Author currentAuthor) {
        Optional<Article> articleOptional = this.articleRepository.findById(saveArticleDTO.getArticleId());
        if (!articleOptional.isPresent()) {
            logger.error("The article with id {} is not exist.", saveArticleDTO.getArticleId());
            throw new ServiceException(ExceptionCode.ARTICLE_ERROR_NOT_EXIST);
        }
        Article article = articleOptional.get();
        if (!article.getAuthorId().equals(currentAuthor.getId())) {
            logger.error("The author is not the owner of article, author id = {}, article id = {}.",
                    currentAuthor.getId(), saveArticleDTO.getArticleId());
            throw new ServiceException(ExceptionCode.ARTICLE_ERROR_AUTHOR_NOT_OWNER);
        }
        if (saveArticleDTO.getAnthologyId() != null) {
            if (!article.getAnthologyId().equals(saveArticleDTO.getAnthologyId())) {
                logger.debug(
                        "The anthology id changed, author maybe want to change the anthology of the article, article id = {} , old anthology id = {}, new anthology id = {}",
                        article.getId(), article.getAnthologyId(), saveArticleDTO.getAnthologyId());
                Optional<Anthology> targetAnthologyOptional =
                        this.anthologyRepository.findById(saveArticleDTO.getAnthologyId());
                if (!targetAnthologyOptional.isPresent()) {
                    logger.error("The target anthology is not exist on update article, anthology id = {}.",
                            saveArticleDTO.getAnthologyId());
                    throw new ServiceException(ExceptionCode.ANTHOLOGY_ERROR_NOT_EXIST);
                }
                boolean isOwner = targetAnthologyOptional.get().getAuthorId().equals(currentAuthor.getId());
                boolean isParticipant =
                        targetAnthologyOptional.get().getParticipantAuthorIds().contains(currentAuthor.getId());
                if (!isOwner && !isParticipant) {
                    logger.error(
                            "The author is not a owner or participant of the target anthology, author id = {}, anthology id = {}.",
                            currentAuthor.getId(), saveArticleDTO.getAnthologyId());
                    throw new ServiceException(ExceptionCode.ANTHOLOGY_ERROR_AUTHOR_NEITHER_OWNER_NOR_PARTICIPANT);
                }
            }
        }
        article.setTitle(saveArticleDTO.getTitle());
        article.setSummary(saveArticleDTO.getSummary());
        ContentParseResultDTO contentParseResult = this.parseArticleContent(saveArticleDTO.getContent());
        article.setContent(contentParseResult.getContent());
        if (!contentParseResult.getResources().isEmpty()) {
            article.setCoverResourceId(contentParseResult.getResources().get(0).getId());
        }
        article.setUpdateDate(new Date());
        article.setTags(saveArticleDTO.getTags());
        this.articleRepository.save(article);
        SaveArticleResultDTO result = new SaveArticleResultDTO();
        result.setArticleId(article.getId());
        result.setAnthologyId(article.getAnthologyId());
        return result;
    }

    private SaveArticleResultDTO createArticle(SaveArticleDTO saveArticleDTO, Author currentAuthor) {
        if (saveArticleDTO.getAnthologyId() == null) {
            logger.debug("The anthology id is empty on create article will save the article to the default anthology.");
            saveArticleDTO
                    .setAnthologyId(currentAuthor.getDefaultAnthologyId());
        } else {
            Optional<Anthology> targetAnthologyOptional =
                    this.anthologyRepository.findById(saveArticleDTO.getAnthologyId());
            if (!targetAnthologyOptional.isPresent()) {
                logger.error("The anthology is not exist on create article, anthology id = {}.",
                        saveArticleDTO.getAnthologyId());
                throw new ServiceException(ExceptionCode.ANTHOLOGY_ERROR_NOT_EXIST);
            }
            boolean isOwner = targetAnthologyOptional.get().getAuthorId().equals(currentAuthor.getId());
            boolean isParticipant =
                    targetAnthologyOptional.get().getParticipantAuthorIds().contains(currentAuthor.getId());
            if (!isOwner && !isParticipant) {
                logger.error(
                        "The author is not a owner or participant of the anthology, author id = {}, anthology id = {}.",
                        currentAuthor.getId(), saveArticleDTO.getAnthologyId());
                throw new ServiceException(ExceptionCode.ANTHOLOGY_ERROR_AUTHOR_NEITHER_OWNER_NOR_PARTICIPANT);
            }
        }
        Article article = new Article();
        article.setTitle(saveArticleDTO.getTitle());
        article.setCreateDate(new Date());
        article.setSummary(saveArticleDTO.getSummary());
        ContentParseResultDTO contentParseResult = this.parseArticleContent(saveArticleDTO.getContent());
        article.setContent(contentParseResult.getContent());
        if (!contentParseResult.getResources().isEmpty()) {
            article.setCoverResourceId(contentParseResult.getResources().get(0).getId());
        }
        article.setUpdateDate(new Date());
        article.setAnthologyId(saveArticleDTO.getAnthologyId());
        article.setAuthorId(currentAuthor.getId());
        this.articleRepository.save(article);
        SaveArticleResultDTO result = new SaveArticleResultDTO();
        result.setArticleId(article.getId());
        result.setAnthologyId(article.getAnthologyId());
        return result;
    }

    private ContentParseResultDTO parseArticleContent(String content) {
        return this.contentService.parse(content);
    }

    @Security
    @PrepareSecurityContext
    @Override
    public PraiseArticleResultDTO praiseArticle(PraiseArticleDTO praiseArticleDTO) {
        Author currentAuthor = SecurityContextHolder.INSTANCE.getContext().getAuthor();
        Optional<Article> articleOptional = this.articleRepository.findById(praiseArticleDTO.getArticleId());
        if (!articleOptional.isPresent()) {
            throw new ServiceException(ExceptionCode.ARTICLE_ERROR_NOT_EXIST);
        }
        Article article = articleOptional.get();
        if (!article.getPraises().containsKey(currentAuthor.getId())) {
            article.getPraises().put(currentAuthor.getId(), new Date());
            article.setPraisesNumber(article.getPraisesNumber() + 1);
        } else {
            article.getPraises().remove(currentAuthor.getId());
            article.setPraisesNumber(article.getPraisesNumber() - 1);
        }
        this.articleRepository.save(article);
        PraiseArticleResultDTO resultDTO = new PraiseArticleResultDTO();
        resultDTO.setArticleId(article.getId());
        resultDTO.setPraiseNumber(article.getPraisesNumber());
        return resultDTO;
    }

    @Security
    @PrepareSecurityContext
    @Override
    public BookmarkArticleResultDTO bookmarkArticle(BookmarkArticleDTO bookmarkArticleDTO) {
        Author currentAuthor = SecurityContextHolder.INSTANCE.getContext().getAuthor();
        Optional<Article> articleOptional = this.articleRepository.findById(bookmarkArticleDTO.getArticleId());
        if (!articleOptional.isPresent()) {
            throw new ServiceException(ExceptionCode.ARTICLE_ERROR_NOT_EXIST);
        }
        Article article = articleOptional.get();
        if (!article.getBookmarks().containsKey(currentAuthor.getId())) {
            article.getBookmarks().put(currentAuthor.getId(), new Date());
            article.setBookmarksNumber(article.getBookmarksNumber() + 1);
        } else {
            article.getBookmarks().remove(currentAuthor.getId());
            article.setBookmarksNumber(article.getBookmarksNumber() - 1);
        }
        this.articleRepository.save(article);
        BookmarkArticleResultDTO resultDTO = new BookmarkArticleResultDTO();
        resultDTO.setArticleId(article.getId());
        resultDTO.setBookmarkNumber(article.getBookmarksNumber());
        return resultDTO;
    }
}
