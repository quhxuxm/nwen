package online.nwen.service.api;

import online.nwen.service.api.exception.ServiceException;
import online.nwen.service.dto.article.SaveArticleDTO;
import online.nwen.service.dto.article.UpdateArticleDTO;

public interface IArticleService {
    String saveArticle(SaveArticleDTO saveArticleDTO) throws ServiceException;

    String updateArticle(UpdateArticleDTO updateArticleDTO)
            throws ServiceException;
}
