package online.nwen.repository;

import online.nwen.domain.Anthology;
import online.nwen.domain.AuthorAnthologyPraise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAuthorAnthologyPraiseRepository
        extends JpaRepository<AuthorAnthologyPraise, AuthorAnthologyPraise.PK> {
    long countByPkAnthology(Anthology anthology);
}
