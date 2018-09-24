package online.nwen.service.impl;

import online.nwen.domain.Author;
import online.nwen.repository.IAuthorRepository;
import online.nwen.service.api.IAuthenticationService;
import online.nwen.service.api.IJWTService;
import online.nwen.service.api.exception.ExceptionCode;
import online.nwen.service.api.exception.ServiceException;
import online.nwen.service.configuration.ServiceConfiguration;
import online.nwen.service.dto.authenticate.AuthenticateDTO;
import online.nwen.service.dto.authenticate.AuthenticateResultDTO;
import online.nwen.service.dto.security.JwtContentDTO;
import online.nwen.service.dto.security.JwtCreateResultDTO;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
class AuthenticationService implements IAuthenticationService {
    private IAuthorRepository authorRepository;
    private PasswordEncoder passwordEncoder;
    private IJWTService jwtService;
    private ServiceConfiguration serviceConfiguration;

    AuthenticationService(IAuthorRepository authorRepository,
                          IJWTService jwtService,
                          PasswordEncoder passwordEncoder,
                          ServiceConfiguration serviceConfiguration) {
        this.authorRepository = authorRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.serviceConfiguration = serviceConfiguration;
    }

    @Override
    public AuthenticateResultDTO authenticate(AuthenticateDTO authenticateDTO) throws ServiceException {
        if (!this.authorRepository.existsByUsername(authenticateDTO.getUsername())) {
            throw new ServiceException(
                    ExceptionCode.AUTHENTICATION_TOKEN_NOT_EXIST);
        }
        Author author = this.authorRepository.findByUsername(authenticateDTO.getUsername());
        if (!passwordEncoder.matches(authenticateDTO.getPassword(), author.getPassword())) {
            throw new ServiceException(
                    ExceptionCode.AUTHENTICATION_PASSWORD_NOT_MATCH);
        }
        author.setLastLoginDate(new Date());
        //Update the last login time.
        this.authorRepository.save(author);
        long currentDateTime = new Date().getTime();
        long jwtExpirationTime = currentDateTime + this.serviceConfiguration.getJwtExpiration();
        long jwtRefreshExpirationTime = currentDateTime + this.serviceConfiguration.getJwtRefreshExpiration();
        JwtContentDTO jwtContent = new JwtContentDTO();
        jwtContent.setAuthorId(author.getId());
        jwtContent.setAuthorIconImageId(author.getIconImageId());
        jwtContent.setAuthorNickname(author.getNickname());
        jwtContent.setAuthorUsername(author.getUsername());
        jwtContent.setAuthorTags(author.getTags());
        jwtContent.setAuthorDefaultAnthologyId(author.getDefaultAnthologyId());
        JwtCreateResultDTO jwtCreateResultDTO =
                this.jwtService.create(jwtContent, jwtExpirationTime, jwtRefreshExpirationTime);
        AuthenticateResultDTO authenticateResultDTO = new AuthenticateResultDTO();
        authenticateResultDTO.setToken(jwtCreateResultDTO.getToken());
        authenticateResultDTO.setExpireTime(jwtCreateResultDTO.getExpiration());
        return authenticateResultDTO;
    }
}
