package online.nwen.repository;

import online.nwen.domain.Resource;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IResourceRepository extends MongoRepository<Resource, String> {
    Resource findByMd5(String md5);
}
