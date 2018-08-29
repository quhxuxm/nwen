package online.nwen.service.api;

import com.quhxuxm.quh.project.simpleblog.domain.Article;
import com.quhxuxm.quh.project.simpleblog.domain.Author;
import com.quhxuxm.quh.project.simpleblog.service.api.exception.ServiceException;
import com.quhxuxm.quh.project.simpleblog.service.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IArticleService {
    Long saveArticle(CreateArticleDTO createArticleDTO) throws ServiceException;

    Long updateArticle(UpdateArticleDTO updateArticleDTO)
            throws ServiceException;

    void assignTagsToArticle(ArticleAssignTagsDTO articleAssignTagsDTO)
            throws ServiceException;

    void bookmarkArticle(ArticleBookmarkDTO articleBookmarkDTO)
            throws ServiceException;

    void praiseArticle(ArticlePraiseDTO articlePraiseDTO)
            throws ServiceException;

    ArticleDetailDTO viewArticle(ArticleViewDTO articleViewDTO)
            throws ServiceException;

    void increaseAuthorTagWeightAccordingToArticleTags(Author author,
                                                       Article article) throws ServiceException;

    Page<ArticleSummaryDTO> listArticleSummariesOrderByCreateDate(
            Pageable pageable, boolean isAsc) throws ServiceException;

    Page<ArticleSummaryDTO> listArticleSummariesOrderByBookmarkNumber(
            Pageable pageable, boolean isAsc) throws ServiceException;

    Page<ArticleSummaryDTO> listArticleSummariesOrderByPraiseNumber(
            Pageable pageable, boolean isAsc) throws ServiceException;

    Page<ArticleSummaryDTO> listArticleSummariesOrderByViewNumber(
            Pageable pageable, boolean isAsc) throws ServiceException;

    Page<ArticleSummaryDTO> listArticleSummariesOrderByCommentNumber(
            Pageable pageable, boolean isAsc) throws ServiceException;

    Page<ArticleSummaryDTO> listArticleSummariesOrderByAuthorInterests(
            Pageable pageable, Long authorId, int topTagsNumber, boolean isAsc)
            throws ServiceException;

    Page<ArticleSummaryDTO> listArticleSummariesInAnthologyOrderByCreateDate(
            Pageable pageable, Long anthologyId, boolean isAsc)
            throws ServiceException;

    Page<ArticleSummaryDTO> listArticleSummariesFromAuthorOrderByCreateDate(
            Pageable pageable, Long authorId, boolean isAsc)
            throws ServiceException;
}
