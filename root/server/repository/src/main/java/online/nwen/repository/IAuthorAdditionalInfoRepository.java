package online.nwen.repository;

import online.nwen.domain.AuthorAdditionalInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAuthorAdditionalInfoRepository extends JpaRepository<AuthorAdditionalInfo, Long> {
}
