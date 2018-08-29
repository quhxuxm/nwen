package online.nwen.repository;

import online.nwen.domain.Author;
import online.nwen.domain.AuthorFollower;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAuthorFollowerRepository
        extends JpaRepository<AuthorFollower, AuthorFollower.PK> {
    long countByPkAuthor(Author author);
}
