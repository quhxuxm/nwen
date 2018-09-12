package online.nwen.service.impl;

import online.nwen.domain.Author;
import online.nwen.repository.IAuthorRepository;
import online.nwen.service.api.IAuthenticationService;
import online.nwen.service.api.IJWTService;
import online.nwen.service.api.exception.ServiceException;
import online.nwen.service.dto.authenticate.AuthenticateDTO;
import online.nwen.service.dto.authenticate.AuthenticateResultDTO;
import online.nwen.service.dto.security.JwtContextDTO;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
class AuthenticationService implements IAuthenticationService {
    private IAuthorRepository authorRepository;
    private PasswordEncoder passwordEncoder;
    private IJWTService securityContextService;

    AuthenticationService(IAuthorRepository authorRepository,
                          IJWTService securityContextService,
                          PasswordEncoder passwordEncoder) {
        this.authorRepository = authorRepository;
        this.securityContextService = securityContextService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public AuthenticateResultDTO authenticate(AuthenticateDTO authenticateDTO) throws ServiceException {
        if (!this.authorRepository.existsByUsername(authenticateDTO.getUsername())) {
            throw new ServiceException(
                    ServiceException.Code.AUTHENTICATION_TOKEN_NOT_EXIST);
        }
        Author author = this.authorRepository.findByUsername(authenticateDTO.getUsername());
        if (!passwordEncoder.matches(authenticateDTO.getPassword(), author.getPassword())) {
            throw new ServiceException(
                    ServiceException.Code.AUTHENTICATION_PASSWORD_NOT_MATCH);
        }
        author.setLastLoginDate(new Date());
        //Update the last login time.
        this.authorRepository.save(author);
        JwtContextDTO jwtContext = this.securityContextService.createJwtTokenWithAuthor(author);
        AuthenticateResultDTO authenticateResultDTO = new AuthenticateResultDTO();
        authenticateResultDTO.setJwtToken(jwtContext.getJwtToken());
        authenticateResultDTO.setExpireTime(jwtContext.getExpiration());
        return authenticateResultDTO;
    }
}
