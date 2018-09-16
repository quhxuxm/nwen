package online.nwen.repository;

import online.nwen.domain.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Set;

public interface IArticleRepository extends MongoRepository<Article, String> {
    Page<Article> findAllByTagsContaining(Set<String> tags, Pageable pageable);
}
