package online.nwen.service.api;

import online.nwen.service.dto.article.SaveArticleDTO;
import online.nwen.service.dto.article.SaveArticleResultDTO;

public interface IArticleService {
    SaveArticleResultDTO saveArticle(SaveArticleDTO saveArticleDTO);
}
