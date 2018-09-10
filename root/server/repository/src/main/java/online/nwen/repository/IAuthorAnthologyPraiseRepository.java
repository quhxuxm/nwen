package online.nwen.repository;

import online.nwen.domain.Anthology;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAuthorAnthologyPraiseRepository
        extends JpaRepository<AuthorAnthologyPraise, AuthorAnthologyPraise.PK> {
    long countByPkAnthology(Anthology anthology);
}
