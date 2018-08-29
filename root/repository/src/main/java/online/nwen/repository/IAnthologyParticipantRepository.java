package online.nwen.repository;

import online.nwen.domain.AnthologyParticipant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAnthologyParticipantRepository
        extends JpaRepository<AnthologyParticipant, AnthologyParticipant.PK> {
    boolean existsByPkAndIsDeletedIsFalse(AnthologyParticipant.PK pk);
}
