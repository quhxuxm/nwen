package online.nwen.service.api;

import online.nwen.service.dto.article.*;

public interface IArticleService {
    ArticleSummaryDTO getArticleSummary(GetArticleSummaryDTO getArticleSummaryDTO);

    ArticleDetailDTO getArticleDetail(GetArticleDetailDTO getArticleDetailDTO);

    SaveArticleResultDTO saveArticle(SaveArticleDTO saveArticleDTO);

    PraiseArticleResultDTO praiseArticle(PraiseArticleDTO praiseArticleDTO);

    BookmarkArticleResultDTO bookmarkArticle(BookmarkArticleDTO bookmarkArticleDTO);
}
