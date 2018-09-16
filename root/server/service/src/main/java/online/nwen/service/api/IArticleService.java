package online.nwen.service.api;

import online.nwen.service.dto.article.*;

public interface IArticleService {
    GetArticleSummaryResultDTO getArticleSummary(GetArticleSummaryDTO getArticleSummaryDTO);

    GetArticleDetailResultDTO getArticleDetail(GetArticleDetailDTO getArticleDetailDTO);

    SaveArticleResultDTO saveArticle(SaveArticleDTO saveArticleDTO);

    PraiseArticleResultDTO praiseArticle(PraiseArticleDTO praiseArticleDTO);

    BookmarkArticleResultDTO bookmarkArticle(BookmarkArticleDTO bookmarkArticleDTO);
}
