package online.nwen.repository;

import online.nwen.domain.Article;
import online.nwen.domain.AuthorArticleBookmark;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAuthorArticleBookmarkRepository
        extends JpaRepository<AuthorArticleBookmark, AuthorArticleBookmark.PK> {
    long countByPkArticle(Article article);
}
