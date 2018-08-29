package online.nwen.service.impl;

import com.quhxuxm.quh.project.simpleblog.common.ICommonConstant;
import com.quhxuxm.quh.project.simpleblog.domain.*;
import com.quhxuxm.quh.project.simpleblog.repository.*;
import com.quhxuxm.quh.project.simpleblog.service.api.IArticleService;
import com.quhxuxm.quh.project.simpleblog.service.api.IAuthorService;
import com.quhxuxm.quh.project.simpleblog.service.api.exception.ServiceException;
import com.quhxuxm.quh.project.simpleblog.service.dto.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceException;
import java.util.*;
import java.util.stream.Collectors;

@Service
class ArticleService implements IArticleService {
    private static final Logger logger = LoggerFactory
            .getLogger(ArticleService.class);
    private ITagRepository tagRepository;
    private IArticleTagRepository articleTagRepository;
    private IAnthologyParticipantRepository anthologyParticipantRepository;
    private IAuthorArticlePraiseRepository authorArticlePraiseRepository;
    private IAuthorRepository authorRepository;
    private IArticleRepository articleRepository;
    private IAnthologyRepository anthologyRepository;
    private IAuthorArticleBookmarkRepository authorArticleBookmarkRepository;
    private IAuthorTagRepository authorTagRepository;
    private IAuthorService authorService;
    private IArticleCommentRepository articleCommentRepository;
    private IAnthologyTagRepository anthologyTagRepository;
    private IArticleAdditionalInfoRepository articleAdditionalInfoRepository;

    ArticleService(ITagRepository tagRepository,
                   IArticleTagRepository articleTagRepository,
                   IAnthologyParticipantRepository anthologyParticipantRepository,
                   IAuthorArticlePraiseRepository authorArticlePraiseRepository,
                   IAuthorRepository authorRepository,
                   IArticleRepository articleRepository,
                   IAnthologyRepository anthologyRepository,
                   IAuthorArticleBookmarkRepository authorArticleBookmarkRepository,
                   IAuthorTagRepository authorTagRepository,
                   IAuthorService authorService,
                   IArticleCommentRepository articleCommentRepository,
                   IAnthologyTagRepository anthologyTagRepository,
                   IArticleAdditionalInfoRepository articleAdditionalInfoRepository) {
        this.tagRepository = tagRepository;
        this.articleTagRepository = articleTagRepository;
        this.anthologyParticipantRepository = anthologyParticipantRepository;
        this.authorArticlePraiseRepository = authorArticlePraiseRepository;
        this.authorRepository = authorRepository;
        this.articleRepository = articleRepository;
        this.anthologyRepository = anthologyRepository;
        this.authorArticleBookmarkRepository = authorArticleBookmarkRepository;
        this.authorTagRepository = authorTagRepository;
        this.authorService = authorService;
        this.articleCommentRepository = articleCommentRepository;
        this.anthologyTagRepository = anthologyTagRepository;
        this.articleAdditionalInfoRepository = articleAdditionalInfoRepository;
    }

    @Transactional
    @Override
    public Long updateArticle(
            UpdateArticleDTO updateArticleDTO) {
        Article article = this.articleRepository
                .getOne(updateArticleDTO.getArticleId());
        article.setTitle(updateArticleDTO.getTitle());
        article.setSummary(updateArticleDTO.getSummary());
        article.setContent(updateArticleDTO.getContent());
        article.setUpdateDate(new Date());
        article.setPublished(updateArticleDTO.isPublished());
        return null;
    }

    @Transactional
    @Override
    public Long saveArticle(CreateArticleDTO createArticleDTO)
            throws ServiceException {
        try {
            Anthology anthology = this.anthologyRepository
                    .getOne(createArticleDTO.getAnthologyId());
            Author author = this.authorRepository
                    .getOne(createArticleDTO.getAuthorId());
            boolean authorOwnAnthology = false;
            if (anthology.getAuthor().getId()
                    .equals(createArticleDTO.getAuthorId())) {
                authorOwnAnthology = true;
            }
            if (!authorOwnAnthology) {
                AnthologyParticipant.PK anthologyParticipantPk = new AnthologyParticipant.PK();
                anthologyParticipantPk.setAnthology(anthology);
                anthologyParticipantPk.setAuthor(author);
                if (!this.anthologyParticipantRepository
                        .existsByPkAndIsDeletedIsFalse(
                                anthologyParticipantPk)) {
                    logger.error(
                            "Author is not a participant of the anthology.");
                    throw new ServiceException(
                            "Author is not a participant of the anthology.",
                            ServiceException.Code.AUTHOR_NOT_PARTICIPANT_OF_ANTHOLOGY);
                }
            }
            Article article = new Article();
            article.setTitle(createArticleDTO.getTitle());
            article.setContent(createArticleDTO.getContent());
            article.setCreateDate(new Date());
            article.setSummary(createArticleDTO.getSummary());
            article.setAnthology(anthology);
            this.articleRepository.save(article);
            author.getAdditionalInfo().setArticleNumber(
                    author.getAdditionalInfo().getArticleNumber() + 1);
            this.authorRepository.save(author);
            Map<String, Boolean> articleTags = new HashMap<>();
            createArticleDTO.getTags().forEach(tagText -> {
                articleTags.put(tagText, true);
            });
            Set<AnthologyTag> anthologyTags = this.anthologyTagRepository
                    .findAllByPkAnthology(anthology);
            anthologyTags.forEach(anthologyTag -> articleTags
                    .put(anthologyTag.getPk().getTag().getText(), false));
            articleTags.forEach((key, value) -> {
                Tag tag = tagRepository.findByText(key);
                if (tag == null) {
                    tag = new Tag();
                    tag.setText(key);
                    tagRepository.save(tag);
                }
                ArticleTag articleTag = new ArticleTag();
                ArticleTag.PK articleTagPk = new ArticleTag.PK();
                articleTagPk.setArticle(article);
                articleTagPk.setTag(tag);
                articleTag.setPk(articleTagPk);
                articleTag.setSelected(value);
                articleTag.setWeight(
                        ICommonConstant.DefaultValue.ARTICLE_SELECTED_TAG_INIT_WEIGHT);
                this.articleTagRepository.save(articleTag);
            });
            return article.getId();
        } catch (PersistenceException e) {
            logger.error("Fail to save article because of exception.", e);
            throw new ServiceException(
                    "Fail to save article because of exception.", e,
                    ServiceException.Code.PERSISTENCE_FAIL);
        }
    }

    @Transactional
    @Override
    public void assignTagsToArticle(ArticleAssignTagsDTO articleAssignTagsDTO)
            throws ServiceException {
        try {
            Article article = this.articleRepository
                    .getOne(articleAssignTagsDTO.getArticleId());
            if (!article.getAnthology().getAuthor().getId()
                    .equals(articleAssignTagsDTO.getAuthorId())) {
                logger.error(
                        "Can not assign tags to article because the author id not the owner of the article.");
                throw new ServiceException(
                        "Can not assign tags to article because the author id not the owner of the article.",
                        ServiceException.Code.AUTHOR_NOT_OWNER_OF_ARTICLE);
            }
            article.setUpdateDate(new Date());
            this.articleRepository.save(article);
            Set<ArticleTag> articleTags = this.articleTagRepository
                    .findAllByPkArticle(article);
            Set<String> tagTextsFromDb = new HashSet<>();
            articleTags.forEach(articleTag -> {
                Tag tag = articleTag.getPk().getTag();
                tagTextsFromDb.add(tag.getText());
            });
            Set<String> tagTextsFromDTO = new HashSet<>(
                    articleAssignTagsDTO.getTags());
            tagTextsFromDTO.removeAll(tagTextsFromDb);
            tagTextsFromDTO.forEach(text -> {
                Tag tag = new Tag();
                tag.setText(text);
                this.tagRepository.save(tag);
                ArticleTag articleTag = new ArticleTag();
                ArticleTag.PK articleTagPk = new ArticleTag.PK();
                articleTagPk.setArticle(article);
                articleTagPk.setTag(tag);
                articleTag.setSelected(true);
                articleTag.setWeight(
                        ICommonConstant.DefaultValue.ARTICLE_SELECTED_TAG_INIT_WEIGHT);
                this.articleTagRepository.save(articleTag);
            });
        } catch (PersistenceException e) {
            logger.error("Fail to assign article tags because of exception.",
                    e);
            throw new ServiceException(
                    "Fail to assign article tags because of exception.", e,
                    ServiceException.Code.PERSISTENCE_FAIL);
        }
    }

    @Transactional
    @Override
    public void bookmarkArticle(ArticleBookmarkDTO articleBookmarkDTO)
            throws ServiceException {
        try {
            Author author = this.authorRepository
                    .getOne(articleBookmarkDTO.getAuthorId());
            Article article = this.articleRepository
                    .getOne(articleBookmarkDTO.getArticleId());
            AuthorArticleBookmark.PK authorArticleBookmarkPk = new AuthorArticleBookmark.PK();
            authorArticleBookmarkPk.setArticle(article);
            authorArticleBookmarkPk.setAuthor(author);
            if (this.authorArticleBookmarkRepository
                    .existsById(authorArticleBookmarkPk)) {
                logger.debug(
                        "Same author will not able bookmark the same article again.");
                return;
            }
            AuthorArticleBookmark authorArticleBookmark = new AuthorArticleBookmark();
            authorArticleBookmark.setMarkDate(new Date());
            authorArticleBookmark.setPk(authorArticleBookmarkPk);
            this.authorArticleBookmarkRepository.save(authorArticleBookmark);
            article.getAdditionalInfo().setBookmarkNumber(
                    this.authorArticleBookmarkRepository
                            .countByPkArticle(article));
            this.articleAdditionalInfoRepository
                    .save(article.getAdditionalInfo());
            this.increaseAuthorTagWeightAccordingToArticleTags(author, article);
        } catch (PersistenceException e) {
            logger.error("Fail to bookmark article because of exception.", e);
            throw new ServiceException(
                    "Fail to bookmark article because of exception.", e,
                    ServiceException.Code.PERSISTENCE_FAIL);
        }
    }

    @Transactional
    @Override
    public void praiseArticle(ArticlePraiseDTO articlePraiseDTO)
            throws ServiceException {
        try {
            Author author = this.authorRepository
                    .getOne(articlePraiseDTO.getAuthorId());
            Article article = this.articleRepository
                    .getOne(articlePraiseDTO.getArticleId());
            AuthorArticlePraise.PK authorArticlePraisePk = new AuthorArticlePraise.PK();
            authorArticlePraisePk.setArticle(article);
            authorArticlePraisePk.setAuthor(author);
            if (this.authorArticlePraiseRepository
                    .existsById(authorArticlePraisePk)) {
                logger.debug(
                        "Same author will not able praise the same article again.");
                return;
            }
            AuthorArticlePraise authorArticlePraise = new AuthorArticlePraise();
            authorArticlePraise.setPraiseDate(new Date());
            authorArticlePraise.setPk(authorArticlePraisePk);
            this.authorArticlePraiseRepository.save(authorArticlePraise);
            article.getAdditionalInfo().setPraiseNumber(
                    this.authorArticlePraiseRepository
                            .countByPkArticle(article));
            this.articleRepository.save(article);
            this.increaseAuthorTagWeightAccordingToArticleTags(author, article);
        } catch (PersistenceException e) {
            logger.error("Fail to praise article because of exception.", e);
            throw new ServiceException(
                    "Fail to praise article because of exception.", e,
                    ServiceException.Code.PERSISTENCE_FAIL);
        }
    }

    @Transactional
    @Override
    public ArticleDetailDTO viewArticle(ArticleViewDTO articleViewDTO)
            throws ServiceException {
        try {
            Article article = this.articleRepository
                    .getOne(articleViewDTO.getArticleId());
            ArticleDetailDTO result = new ArticleDetailDTO();
            result.setArticleId(article.getId());
            result.setContent(article.getContent());
            result.setSummary(article.getSummary());
            if (article.getAnthology().getAuthor().getIconImage() != null) {
                result.setAuthorIconImageId(
                        article.getAnthology().getAuthor().getIconImage()
                                .getId());
            }
            if (article.getAnthology().getCoverImage() != null) {
                result.setAnthologyCoverImageId(
                        article.getAnthology().getCoverImage().getId());
            }
            result.setAnthologyTitle(article.getAnthology().getTitle());
            result.setAuthorNickName(
                    article.getAnthology().getAuthor().getNickName());
            result.setTitle(article.getTitle());
            result.setAuthorId(article.getAnthology().getAuthor().getId());
            result.setAnthologyId(article.getAnthology().getId());
            result.setBookmarkNumber(
                    article.getAdditionalInfo().getBookmarkNumber());
            result.setCommentNumber(
                    article.getAdditionalInfo().getCommentNumber());
            result.setUpdateDate(article.getUpdateDate());
            result.setCreateDate(article.getCreateDate());
            result.setPraiseNumber(
                    article.getAdditionalInfo().getPraiseNumber());
            result.setViewNumber(article.getAdditionalInfo().getViewNumber());
            if (articleViewDTO.getAuthorId() != null) {
                Author author = this.authorRepository
                        .getOne(articleViewDTO.getAuthorId());
                this.increaseAuthorTagWeightAccordingToArticleTags(author,
                        article);
                article.getAdditionalInfo().setViewNumber(
                        article.getAdditionalInfo().getViewNumber() + 1);
                this.articleAdditionalInfoRepository
                        .save(article.getAdditionalInfo());
            }
            Set<ArticleTag> articleTags = this.articleTagRepository
                    .findAllByPkArticle(article);
            articleTags.forEach(articleTag -> {
                result.getTags().add(articleTag.getPk().getTag().getText());
            });
            return result;
        } catch (PersistenceException e) {
            throw new ServiceException(
                    "Can not view article because of exception.",
                    ServiceException.Code.PERSISTENCE_FAIL);
        }
    }

    @Transactional
    @Override
    public void increaseAuthorTagWeightAccordingToArticleTags(Author author,
                                                              Article article) throws ServiceException {
        try {
            Set<ArticleTag> articleTags = this.articleTagRepository
                    .findAllByPkArticle(article);
            Set<Tag> tagsFromArticle = articleTags.stream()
                    .map(tag -> tag.getPk().getTag())
                    .collect(Collectors.toSet());
            Set<AuthorTag> authorTags = this.authorTagRepository
                    .findAllByPkAuthor(author);
            Set<Tag> tagsFromAuthor = authorTags.stream()
                    .map(tag -> tag.getPk().getTag())
                    .collect(Collectors.toSet());
            tagsFromAuthor.addAll(tagsFromArticle);
            AuthorAssignTagsDTO authorAssignTagsDTO = new AuthorAssignTagsDTO();
            authorAssignTagsDTO.setAuthorId(author.getId());
            authorAssignTagsDTO.setSelect(false);
            tagsFromAuthor.forEach(
                    tag -> authorAssignTagsDTO.getTags().add(tag.getText()));
            this.authorService.assignTagsToAuthor(authorAssignTagsDTO);
        } catch (PersistenceException e) {
            logger.error(
                    "Can not increase author tag weight according to article because of exception.",
                    e);
            throw new ServiceException(
                    "Can not increase author tag weight according to article because of exception.",
                    e, ServiceException.Code.PERSISTENCE_FAIL);
        }
    }

    private ArticleSummaryDTO convertPojoToDto(Article pojo) {
        ArticleSummaryDTO dto = new ArticleSummaryDTO();
        dto.setArticleId(pojo.getId());
        dto.setUpdateDate(pojo.getUpdateDate());
        dto.setCreateDate(pojo.getCreateDate());
        dto.setAuthorNickName(pojo.getAnthology().getAuthor().getNickName());
        dto.setTitle(pojo.getTitle());
        dto.setAuthorId(pojo.getAnthology().getAuthor().getId());
        if (pojo.getAnthology().getCoverImage() != null) {
            dto.setAnthologyCoverImageId(
                    pojo.getAnthology().getCoverImage().getId());
        }
        dto.setAnthologyId(pojo.getAnthology().getId());
        dto.setAnthologyTitle(pojo.getAnthology().getTitle());
        if (pojo.getAnthology().getAuthor().getIconImage() != null) {
            dto.setAuthorIconImageId(
                    pojo.getAnthology().getAuthor().getIconImage().getId());
        }
        dto.setCommentNumber(pojo.getAdditionalInfo().getCommentNumber());
        dto.setBookmarkNumber(pojo.getAdditionalInfo().getBookmarkNumber());
        dto.setPraiseNumber(pojo.getAdditionalInfo().getPraiseNumber());
        dto.setViewNumber(pojo.getAdditionalInfo().getViewNumber());
        dto.setSummary(pojo.getSummary());
        if (pojo.getCoverImage() != null) {
            dto.setCoverImageId(pojo.getCoverImage().getId());
        }
        return dto;
    }

    @Override
    public Page<ArticleSummaryDTO> listArticleSummariesOrderByBookmarkNumber(
            Pageable pageable, boolean isAsc) throws ServiceException {
        try {
            Page<Article> domainObjPage;
            if (isAsc) {
                domainObjPage = this.articleRepository
                        .findAllByOrderByAdditionalInfoBookmarkNumberAsc(
                                pageable);
            } else {
                domainObjPage = this.articleRepository
                        .findAllByOrderByAdditionalInfoBookmarkNumberDesc(
                                pageable);
            }
            return domainObjPage.map(this::convertPojoToDto);
        } catch (PersistenceException e) {
            throw new ServiceException(
                    "Can not find articles order by bookmark number.", e,
                    ServiceException.Code.PERSISTENCE_FAIL);
        }
    }

    @Override
    public Page<ArticleSummaryDTO> listArticleSummariesOrderByPraiseNumber(
            Pageable pageable, boolean isAsc) throws ServiceException {
        try {
            Page<Article> domainObjPage;
            if (isAsc) {
                domainObjPage = this.articleRepository
                        .findAllByOrderByAdditionalInfoPraiseNumberAsc(
                                pageable);
            } else {
                domainObjPage = this.articleRepository
                        .findAllByOrderByAdditionalInfoPraiseNumberDesc(
                                pageable);
            }
            return domainObjPage.map(this::convertPojoToDto);
        } catch (PersistenceException e) {
            throw new ServiceException(
                    "Can not list articles order by praise number.", e,
                    ServiceException.Code.PERSISTENCE_FAIL);
        }
    }

    @Override
    public Page<ArticleSummaryDTO> listArticleSummariesOrderByViewNumber(
            Pageable pageable, boolean isAsc) throws ServiceException {
        try {
            Page<Article> domainObjPage;
            if (isAsc) {
                domainObjPage = this.articleRepository
                        .findAllByOrderByAdditionalInfoViewNumberAsc(pageable);
            } else {
                domainObjPage = this.articleRepository
                        .findAllByOrderByAdditionalInfoViewNumberDesc(pageable);
            }
            return domainObjPage.map(this::convertPojoToDto);
        } catch (PersistenceException e) {
            throw new ServiceException(
                    "Can not list articles order by view number.", e,
                    ServiceException.Code.PERSISTENCE_FAIL);
        }
    }

    @Override
    public Page<ArticleSummaryDTO> listArticleSummariesOrderByCommentNumber(
            Pageable pageable, boolean isAsc) throws ServiceException {
        try {
            Page<Article> domainObjPage;
            if (isAsc) {
                domainObjPage = this.articleRepository
                        .findAllByOrderByAdditionalInfoCommentNumberAsc(
                                pageable);
            } else {
                domainObjPage = this.articleRepository
                        .findAllByOrderByAdditionalInfoCommentNumberDesc(
                                pageable);
            }
            return domainObjPage.map(this::convertPojoToDto);
        } catch (PersistenceException e) {
            throw new ServiceException(
                    "Can not list articles order by comment number.",
                    ServiceException.Code.PERSISTENCE_FAIL);
        }
    }

    @Override
    public Page<ArticleSummaryDTO> listArticleSummariesOrderByAuthorInterests(
            Pageable pageable, Long authorId, int topTagsNumber, boolean isAsc)
            throws ServiceException {
        try {
            Author author = this.authorRepository.getOne(authorId);
            Set<AuthorTag> authorTags = this.authorTagRepository
                    .findAllByPkAuthor(author);
            List<AuthorTag> orderedAuthorTags = new ArrayList<>(authorTags);
            orderedAuthorTags
                    .sort((o1, o2) -> (o1.getWeight().compareTo(o2.getWeight()))
                            * (-1));
            List<AuthorTag> topNumberOfAuthorTags;
            if (orderedAuthorTags.size() > topTagsNumber) {
                topNumberOfAuthorTags = orderedAuthorTags
                        .subList(0, topTagsNumber - 1);
            } else {
                if (orderedAuthorTags.size() == 0) {
                    topNumberOfAuthorTags = new ArrayList<>();
                } else {
                    topNumberOfAuthorTags = orderedAuthorTags
                            .subList(0, orderedAuthorTags.size() - 1);
                }
            }
            List<Tag> authorInterestTagsOrderedByWeight = new ArrayList<>();
            topNumberOfAuthorTags.forEach(
                    authorTag -> authorInterestTagsOrderedByWeight
                            .add(authorTag.getPk().getTag()));
            Page<ArticleTag> articleTags = this.articleTagRepository
                    .findAllByPkTagIn(pageable,
                            authorInterestTagsOrderedByWeight);
            return articleTags.map(articleTag -> this
                    .convertPojoToDto(articleTag.getPk().getArticle()));
        } catch (PersistenceException e) {
            throw new ServiceException(
                    "Can not list articles by author interests.", e,
                    ServiceException.Code.PERSISTENCE_FAIL);
        }
    }

    @Override
    public Page<ArticleSummaryDTO> listArticleSummariesInAnthologyOrderByCreateDate(
            Pageable pageable, Long anthologyId, boolean isAsc)
            throws ServiceException {
        try {
            Anthology anthology = this.anthologyRepository.getOne(anthologyId);
            Page<Article> domainObjPage;
            if (isAsc) {
                domainObjPage = this.articleRepository
                        .findAllByAnthologyEqualsOrderByCreateDateAsc(pageable,
                                anthology);
            } else {
                domainObjPage = this.articleRepository
                        .findAllByAnthologyEqualsOrderByCreateDateDesc(pageable,
                                anthology);
            }
            return domainObjPage.map(this::convertPojoToDto);
        } catch (PersistenceException e) {
            throw new ServiceException(
                    "Can not list the articles in the anthology because of exception.",
                    e, ServiceException.Code.PERSISTENCE_FAIL);
        }
    }

    @Override
    public Page<ArticleSummaryDTO> listArticleSummariesFromAuthorOrderByCreateDate(
            Pageable pageable, Long authorId, boolean isAsc)
            throws ServiceException {
        try {
            Author author = this.authorRepository.getOne(authorId);
            Page<Article> domainObjPage;
            if (isAsc) {
                domainObjPage = this.articleRepository
                        .findAllByAnthologyAuthorEqualsOrderByCreateDateAsc(
                                pageable, author);
            } else {
                domainObjPage = this.articleRepository
                        .findAllByAnthologyAuthorEqualsOrderByCreateDateDesc(
                                pageable, author);
            }
            return domainObjPage.map(this::convertPojoToDto);
        } catch (PersistenceException e) {
            throw new ServiceException(
                    "Can not list the articles in the author because of exception.",
                    e, ServiceException.Code.PERSISTENCE_FAIL);
        }
    }

    @Override
    public Page<ArticleSummaryDTO> listArticleSummariesOrderByCreateDate(
            Pageable pageable, boolean isAsc) throws ServiceException {
        try {
            Page<Article> domainObjPage;
            if (isAsc) {
                domainObjPage = this.articleRepository
                        .findAllByOrderByCreateDateAsc(pageable);
            } else {
                domainObjPage = this.articleRepository
                        .findAllByOrderByCreateDateDesc(pageable);
            }
            return domainObjPage.map(this::convertPojoToDto);
        } catch (PersistenceException e) {
            throw new ServiceException(
                    "Can not list articles order by view number.", e,
                    ServiceException.Code.PERSISTENCE_FAIL);
        }
    }
}
