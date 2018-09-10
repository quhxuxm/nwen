package online.nwen.repository;

import online.nwen.domain.Anthology;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface IAnthologyTagRepository
        extends JpaRepository<AnthologyTag, AnthologyTag.PK> {
    Set<AnthologyTag> findAllByPkAnthology(Anthology anthology);

    Set<ArticleTag> findAllByPkAnthologyAndIsSelectedIsTrue(Anthology anthology);
}
