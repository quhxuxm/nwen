package online.nwen.repository;

import online.nwen.domain.Article;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IArticleRepository extends MongoRepository<Article, String> {
}
