package online.nwen.repository;

import online.nwen.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAuthorRepository extends JpaRepository<Author, Long> {
    Author findByToken(String token);

    boolean existsByToken(String token);

    boolean existsByNickName(String nickName);
}
