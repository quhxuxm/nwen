package online.nwen.repository;

import online.nwen.domain.Article;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.mongodb.repository.MongoRepository;

@Cacheable
public interface IArticleRepository extends MongoRepository<Article, String> {
}
