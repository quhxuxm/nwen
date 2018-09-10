package online.nwen.repository;

import online.nwen.domain.Anthology;
import online.nwen.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAnthologyCommentRepository
        extends JpaRepository<AnthologyComment, Long> {
    long countByAuthor(Author author);

    long countByAnthology(Anthology anthology);
}
