package online.nwen.repository;

import online.nwen.domain.Anthology;
import online.nwen.domain.Article;
import online.nwen.domain.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IArticleRepository extends JpaRepository<Article, Long> {
    long countByAnthologyAuthor(Author author);

    Page<Article> findAllByOrderByCreateDateAsc(Pageable pageable);

    Page<Article> findAllByOrderByCreateDateDesc(Pageable pageable);

    Page<Article> findAllByOrderByAdditionalInfoBookmarkNumberDesc(
            Pageable pageable);

    Page<Article> findAllByOrderByAdditionalInfoBookmarkNumberAsc(
            Pageable pageable);

    Page<Article> findAllByOrderByAdditionalInfoViewNumberDesc(
            Pageable pageable);

    Page<Article> findAllByOrderByAdditionalInfoViewNumberAsc(
            Pageable pageable);

    Page<Article> findAllByOrderByAdditionalInfoCommentNumberDesc(
            Pageable pageable);

    Page<Article> findAllByOrderByAdditionalInfoCommentNumberAsc(
            Pageable pageable);

    Page<Article> findAllByOrderByAdditionalInfoPraiseNumberDesc(
            Pageable pageable);

    Page<Article> findAllByOrderByAdditionalInfoPraiseNumberAsc(
            Pageable pageable);

    Page<Article> findAllByAnthologyEqualsOrderByCreateDateAsc(
            Pageable pageable, Anthology anthology);

    Page<Article> findAllByAnthologyEqualsOrderByCreateDateDesc(
            Pageable pageable, Anthology anthology);

    Page<Article> findAllByAnthologyAuthorEqualsOrderByCreateDateAsc(
            Pageable pageable, Author author);

    Page<Article> findAllByAnthologyAuthorEqualsOrderByCreateDateDesc(
            Pageable pageable, Author author);
}
