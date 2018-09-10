package online.nwen.repository;

import online.nwen.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAuthorArticlePraiseRepository
        extends JpaRepository<AuthorArticlePraise, AuthorArticlePraise.PK> {
    long countByPkArticle(Article article);
}
