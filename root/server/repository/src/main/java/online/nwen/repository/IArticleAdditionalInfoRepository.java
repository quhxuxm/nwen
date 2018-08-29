package online.nwen.repository;

import online.nwen.domain.ArticleAdditionalInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IArticleAdditionalInfoRepository extends JpaRepository<ArticleAdditionalInfo, Long> {
}
