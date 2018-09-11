package online.nwen.service.api;

import online.nwen.service.api.exception.ServiceException;
import online.nwen.service.dto.author.AuthorDetailDTO;
import online.nwen.service.dto.author.RegisterAuthorDTO;
import online.nwen.service.dto.author.RegisterAuthorResultDTO;

public interface IAuthorService {
    RegisterAuthorResultDTO register(RegisterAuthorDTO registerAuthorDTO) throws ServiceException;

    AuthorDetailDTO findDetailById(String id) throws ServiceException;
}
