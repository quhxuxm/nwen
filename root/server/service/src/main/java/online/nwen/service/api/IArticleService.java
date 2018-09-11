package online.nwen.service.api;

import online.nwen.service.dto.article.CreateArticleDTO;
import online.nwen.service.dto.article.CreateArticleResultDTO;
import online.nwen.service.dto.article.UpdateArticleDTO;

public interface IArticleService {
    CreateArticleResultDTO createArticle(CreateArticleDTO createArticleDTO, String authorId);

    String updateArticle(UpdateArticleDTO updateArticleDTO, String authorId);
}
