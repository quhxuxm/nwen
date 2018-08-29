package online.nwen.repository;

import online.nwen.domain.Resource;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IResourceRepository extends JpaRepository<Resource, Long> {
}
