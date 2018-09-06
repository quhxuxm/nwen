package online.nwen.service.api;

import online.nwen.service.api.exception.ServiceException;
import online.nwen.service.dto.authenticate.AuthenticateDTO;
import online.nwen.service.dto.authenticate.AuthenticateResultDTO;

public interface IAuthenticationService {
    AuthenticateResultDTO authenticate(AuthenticateDTO authenticateDTO)
            throws ServiceException;
}
