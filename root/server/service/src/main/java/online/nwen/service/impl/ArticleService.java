package online.nwen.service.impl;

import online.nwen.domain.Anthology;
import online.nwen.domain.Article;
import online.nwen.repository.IAnthologyRepository;
import online.nwen.repository.IArticleRepository;
import online.nwen.repository.IAuthorRepository;
import online.nwen.service.api.IArticleService;
import online.nwen.service.api.IAuthorService;
import online.nwen.service.api.exception.ServiceException;
import online.nwen.service.dto.article.CreateArticleDTO;
import online.nwen.service.dto.article.CreateArticleResultDTO;
import online.nwen.service.dto.article.UpdateArticleDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

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
            UpdateArticleDTO updateArticleDTO, String authorId) {
        return null;
    }

    @Override
    public CreateArticleResultDTO createArticle(CreateArticleDTO createArticleDTO, String authorId) {
        Article article = new Article();
        article.setTitle(createArticleDTO.getTitle());
        article.setCreateDate(new Date());
        article.setSummary(createArticleDTO.getSummary());
        article.setContent(createArticleDTO.getContent());
        article.setUpdateDate(new Date());
        Optional<Anthology> targetAnthologyOptional =
                this.anthologyRepository.findById(createArticleDTO.getAnthologyId());
        if (!targetAnthologyOptional.isPresent()) {
            throw new ServiceException(ServiceException.Code.ANTHOLOGY_NOT_EXIST_ERROR);
        }
        boolean isOwner = targetAnthologyOptional.get().getAuthorId().equals(authorId);
        boolean isParticipant = targetAnthologyOptional.get().getParticipantAuthorIds().contains(authorId);
        if (!isOwner && !isParticipant) {
            throw new ServiceException(ServiceException.Code.AUTHOR_NOT_OWNER_OR_PARTICIPANT_OF_ANTHOLOGY_ERROR);
        }
        article.setAnthologyId(createArticleDTO.getAnthologyId());
        this.articleRepository.save(article);
        CreateArticleResultDTO result = new CreateArticleResultDTO();
        result.setArticleId(article.getId());
        return result;
    }
}
