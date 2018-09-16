package online.nwen.service.api;

import online.nwen.service.dto.article.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.util.Set;

public interface IArticleService {
    Page<GetArticleSummaryResultDTO> getArticleSummariesByAnthology(String anthologyId, int currentPage, Sort sort);

    Page<GetArticleSummaryResultDTO> getArticleSummariesByTag(Set<String> tags, int currentPage, Sort sort);

    GetArticleSummaryResultDTO getArticleSummary(GetArticleSummaryDTO getArticleSummaryDTO);

    GetArticleDetailResultDTO getArticleDetail(GetArticleDetailDTO getArticleDetailDTO);

    SaveArticleResultDTO saveArticle(SaveArticleDTO saveArticleDTO);

    PraiseArticleResultDTO praiseArticle(PraiseArticleDTO praiseArticleDTO);

    BookmarkArticleResultDTO bookmarkArticle(BookmarkArticleDTO bookmarkArticleDTO);
}
