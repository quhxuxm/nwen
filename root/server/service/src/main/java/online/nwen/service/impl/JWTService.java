package online.nwen.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import online.nwen.domain.Author;
import online.nwen.repository.IAuthorRepository;
import online.nwen.service.api.IJWTService;
import online.nwen.service.api.exception.ExceptionCode;
import online.nwen.service.api.exception.SecurityException;
import online.nwen.service.api.exception.ServiceException;
import online.nwen.service.configuration.ServiceConfiguration;
import online.nwen.service.dto.security.JwtContextDTO;
import online.nwen.service.security.NoSecurityContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Optional;

@Service
class JWTService implements IJWTService {
    private static final Logger logger = LoggerFactory.getLogger(JWTService.class);
    private ServiceConfiguration serviceConfiguration;
    private Algorithm algorithm;
    private JWTVerifier jwtVerifier;
    private IAuthorRepository authorRepository;

    JWTService(ServiceConfiguration serviceConfiguration, IAuthorRepository authorRepository) {
        this.serviceConfiguration = serviceConfiguration;
        this.authorRepository = authorRepository;
        try {
            this.algorithm = Algorithm.HMAC256(this.serviceConfiguration.getJwtSecret());
        } catch (UnsupportedEncodingException e) {
            throw new ServiceException(ExceptionCode.SYSTEM_ERROR);
        }
        this.jwtVerifier = JWT.require(algorithm)
                .withIssuer(serviceConfiguration.getJwtIssuer())
                .build();
    }

    @NoSecurityContext
    @Override
    public JwtContextDTO createJwtTokenWithAuthor(Author author) {
        long expireTime = System.currentTimeMillis() + this.serviceConfiguration.getJwtExpiration();
        String jwtToken = JWT.create()
                .withIssuer(this.serviceConfiguration.getJwtIssuer())
                .withSubject(author.getId())
                .withExpiresAt(new Date(expireTime))
                .sign(algorithm);
        return new JwtContextDTO(jwtToken, expireTime);
    }

    @NoSecurityContext
    @Override
    public void verify(String jwtToken) {
        try {
            this.jwtVerifier.verify(jwtToken);
        } catch (Exception e) {
            logger.error("Fail to verify jwt token.");
            throw new SecurityException(ExceptionCode.SECURITY_ERROR_JWT_TOKEN_INVALID);
        }
    }

    @NoSecurityContext
    @Override
    public Author getAuthorFromJwtToken(String jwtToken) {
        try {
            DecodedJWT decodedJWT = JWT.decode(jwtToken);
            String authorId = decodedJWT.getSubject();
            Optional<Author> authorOptional = this.authorRepository.findById(authorId);
            if (!authorOptional.isPresent()) {
                logger.error("Can not load the author with jwt token subject, subject = {}", decodedJWT.getSubject());
                throw new SecurityException(ExceptionCode.SECURITY_ERROR_JWT_TOKEN_INVALID);
            }
            return authorOptional.get();
        } catch (Exception e) {
            logger.error("Can not decode the jwt token: {}", jwtToken);
            throw new SecurityException(e, ExceptionCode.SECURITY_ERROR_JWT_TOKEN_INVALID);
        }
    }
}
