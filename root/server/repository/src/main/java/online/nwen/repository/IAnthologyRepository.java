package online.nwen.repository;

import online.nwen.domain.Anthology;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.mongodb.repository.MongoRepository;

@Cacheable
public interface IAnthologyRepository extends MongoRepository<Anthology, String> {
    long countByAuthorId(String authorId);
}
