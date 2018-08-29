package online.nwen.repository;

import online.nwen.domain.Author;
import online.nwen.domain.AuthorDefaultAnthology;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAuthorDefaultAnthologyRepository extends
        JpaRepository<AuthorDefaultAnthology, AuthorDefaultAnthology.PK> {
    AuthorDefaultAnthology findByPkAuthor(Author author);
}
