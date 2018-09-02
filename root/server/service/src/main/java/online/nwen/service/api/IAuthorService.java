package online.nwen.service.api;

import online.nwen.service.api.exception.ServiceException;
import online.nwen.service.dto.author.*;

public interface IAuthorService {
    RegisterAuthorResultDTO register(RegisterAuthorDTO registerAuthorDTO) throws ServiceException;

    AuthorDetailDTO findDetailById(Long id) throws ServiceException;

    AuthorDetailDTO loginByToken(String token) throws ServiceException;

    void assignTagsToAuthor(AuthorAssignTagsDTO authorAssignTagsDTO)
            throws ServiceException;

    void assignFollowerToAuthor(AuthorAssignFollowerDTO authorAssignFollowerDTO)
            throws ServiceException;

    AuthorAuthenticateDTO findForAuthenticate(String token)
            throws ServiceException;
}
