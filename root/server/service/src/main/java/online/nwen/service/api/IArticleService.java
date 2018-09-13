package online.nwen.service.api;

import online.nwen.service.dto.article.*;

public interface IArticleService {
    ViewArticleResultDTO viewArticle(ViewArticleDTO viewArticleDTO);

    SaveArticleResultDTO saveArticle(SaveArticleDTO saveArticleDTO);

    PraiseArticleResultDTO praiseArticle(PraiseArticleDTO praiseArticleDTO);

    BookmarkArticleResultDTO bookmarkArticle(BookmarkArticleDTO bookmarkArticleDTO);
}
