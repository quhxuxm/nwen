package online.nwen.repository;

import online.nwen.domain.Article;
import online.nwen.domain.Comment;
import online.nwen.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IArticleCommentRepository
        extends JpaRepository<Comment, Long> {
    long countByAuthor(Author author);

    long countByArticle(Article article);
}
