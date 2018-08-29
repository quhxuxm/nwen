package online.nwen.repository;

import online.nwen.domain.Author;
import online.nwen.domain.AuthorTag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface IAuthorTagRepository
        extends JpaRepository<AuthorTag, AuthorTag.PK> {
    Set<AuthorTag> findAllByPkAuthorAndIsSelectedIsTrue(Author author);

    Set<AuthorTag> findAllByPkAuthor(Author author);
}
