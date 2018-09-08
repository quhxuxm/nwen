package online.nwen.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import online.nwen.domain.Author;
import online.nwen.repository.IAuthorRepository;
import online.nwen.service.api.IAuthenticationService;
import online.nwen.service.api.exception.ServiceException;
import online.nwen.service.configuration.JwtConfiguration;
import online.nwen.service.dto.authenticate.AuthenticateDTO;
import online.nwen.service.dto.authenticate.AuthenticateResultDTO;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.PersistenceException;
import java.io.UnsupportedEncodingException;
import java.util.Date;

@Service
public class AuthenticationService implements IAuthenticationService {
    private IAuthorRepository authorRepository;
    private JwtConfiguration jwtConfiguration;
    private PasswordEncoder passwordEncoder;

    public AuthenticationService(IAuthorRepository authorRepository, JwtConfiguration jwtConfiguration,
                                 PasswordEncoder passwordEncoder) {
        this.authorRepository = authorRepository;
        this.jwtConfiguration = jwtConfiguration;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public AuthenticateResultDTO authenticate(AuthenticateDTO authenticateDTO) throws ServiceException {
        try {
            if (!this.authorRepository.existsByToken(authenticateDTO.getToken())) {
                throw new ServiceException(
                        ServiceException.Code.AUTHENTICATION_TOKEN_NOT_EXIST);
            }
            Author author = this.authorRepository.findByToken(authenticateDTO.getToken());
            if (!passwordEncoder.matches(authenticateDTO.getPassword(), author.getPassword())) {
                throw new ServiceException(
                        ServiceException.Code.AUTHENTICATION_PASSWORD_NOT_MATCH);
            }
            author.setLastLoginDate(new Date());
            //Update the last login time.
            this.authorRepository.save(author);
            AuthenticateResultDTO authenticateResultDTO = new AuthenticateResultDTO();
            Algorithm algorithm = Algorithm.HMAC256(this.jwtConfiguration.getSecret());
            long expireTime = System.currentTimeMillis() + this.jwtConfiguration.getExpiration();
            String jwtToken = JWT.create()
                    .withIssuer(this.jwtConfiguration.getIssuer())
                    .withSubject(author.getId().toString())
                    .withExpiresAt(new Date(expireTime))
                    .sign(algorithm);
            authenticateResultDTO.setJwtToken(jwtToken);
            authenticateResultDTO.setExpireTime(expireTime);
            return authenticateResultDTO;
        } catch (PersistenceException | UnsupportedEncodingException e) {
            throw new ServiceException(e,
                    ServiceException.Code.SYSTEM_ERROR);
        }
    }
}
