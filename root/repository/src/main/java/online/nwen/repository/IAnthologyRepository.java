package online.nwen.repository;

import online.nwen.domain.Anthology;
import online.nwen.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAnthologyRepository extends JpaRepository<Anthology, Long> {
    long countByAuthor(Author author);
}
