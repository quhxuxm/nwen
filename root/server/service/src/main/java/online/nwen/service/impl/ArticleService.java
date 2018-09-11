package online.nwen.service.impl;

import online.nwen.repository.IAnthologyRepository;
import online.nwen.repository.IArticleRepository;
import online.nwen.repository.IAuthorRepository;
import online.nwen.service.api.IArticleService;
import online.nwen.service.api.IAuthorService;
import online.nwen.service.api.exception.ServiceException;
import online.nwen.service.dto.article.SaveArticleDTO;
import online.nwen.service.dto.article.UpdateArticleDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
class ArticleService implements IArticleService {
    private static final Logger logger = LoggerFactory
            .getLogger(ArticleService.class);
    private IAuthorRepository authorRepository;
    private IArticleRepository articleRepository;
    private IAnthologyRepository anthologyRepository;
    private IAuthorService authorService;

    ArticleService(
            IAuthorRepository authorRepository,
            IArticleRepository articleRepository,
            IAnthologyRepository anthologyRepository,
            IAuthorService authorService) {
        this.authorRepository = authorRepository;
        this.articleRepository = articleRepository;
        this.anthologyRepository = anthologyRepository;
        this.authorService = authorService;
    }

    @Override
    public String updateArticle(
            UpdateArticleDTO updateArticleDTO) {
        return null;
    }

    @Override
    public String saveArticle(SaveArticleDTO saveArticleDTO)
            throws ServiceException {
        return null;
    }
}
