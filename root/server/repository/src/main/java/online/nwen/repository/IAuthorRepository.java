package online.nwen.repository;

import online.nwen.domain.Author;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.mongodb.repository.MongoRepository;

@Cacheable
public interface IAuthorRepository extends MongoRepository<Author, String> {
    Author findByUsername(String username);

    boolean existsByUsername(String username);

    boolean existsByNickname(String nickname);
}
