package online.nwen.repository;

import online.nwen.domain.Author;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IAuthorRepository extends MongoRepository<Author, String> {
    Author findByToken(String token);

    boolean existsByToken(String token);

    boolean existsByNickName(String nickName);
}
