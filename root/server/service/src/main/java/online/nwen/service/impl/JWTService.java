package online.nwen.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import online.nwen.service.api.IJWTService;
import online.nwen.service.api.exception.ExceptionCode;
import online.nwen.service.api.exception.SecurityException;
import online.nwen.service.api.exception.ServiceException;
import online.nwen.service.configuration.ServiceConfiguration;
import online.nwen.service.dto.security.JwtContentDTO;
import online.nwen.service.dto.security.JwtCreateResultDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;

@Service
class JWTService implements IJWTService {
    private static final Logger logger = LoggerFactory.getLogger(JWTService.class);
    private static final String CONTENT_CLAIM_NAME = "content";
    private ServiceConfiguration serviceConfiguration;
    private Algorithm algorithm;
    private JWTVerifier jwtVerifier;
    private ObjectMapper objectMapper;

    JWTService(ServiceConfiguration serviceConfiguration) {
        this.serviceConfiguration = serviceConfiguration;
        this.objectMapper = new ObjectMapper();
        try {
            this.algorithm = Algorithm.HMAC256(this.serviceConfiguration.getJwtSecret());
        } catch (UnsupportedEncodingException e) {
            logger.error("Fail to create jwt algorithm because of exception.", e);
            throw new ServiceException(ExceptionCode.SYSTEM_ERROR);
        }
        this.jwtVerifier = JWT.require(algorithm)
                .withIssuer(serviceConfiguration.getJwtIssuer())
                .build();
    }

    @Override
    public JwtCreateResultDTO create(JwtContentDTO content, long expiration, long refreshExpiration) {
        try {
            Date jwtCreateDate = new Date();
            String token = JWT.create()
                    .withIssuer(this.serviceConfiguration.getJwtIssuer())
                    .withIssuedAt(jwtCreateDate)
                    .withSubject(content.getAuthorId())
                    .withExpiresAt(new Date(expiration))
                    .withNotBefore(jwtCreateDate)
                    .withClaim(CONTENT_CLAIM_NAME, this.objectMapper.writeValueAsString(content))
                    .sign(algorithm);
            return new JwtCreateResultDTO(token, expiration, refreshExpiration);
        } catch (JsonProcessingException e) {
            throw new ServiceException(e, ExceptionCode.SYSTEM_ERROR);
        }
    }

    @Override
    public void verify(String jwtToken) {
        try {
            this.jwtVerifier.verify(jwtToken);
        } catch (TokenExpiredException e) {
            logger.error("Fail to verify jwt token because of token expired.");
            throw new SecurityException(ExceptionCode.SECURITY_ERROR_JWT_TOKEN_EXPIRED);
        } catch (SignatureVerificationException e) {
            logger.error("Fail to verify jwt token because of signature invalid.");
            throw new SecurityException(ExceptionCode.SECURITY_ERROR_JWT_TOKEN_SIGNATURE_INVALID);
        }
    }

    @Override
    public JwtContentDTO parse(String token) {
        try {
            DecodedJWT decodedJWT = JWT.decode(token);
            Claim contentClaim = decodedJWT.getClaim(CONTENT_CLAIM_NAME);
            String contentString = contentClaim.asString();
            return this.objectMapper.readerFor(JwtContentDTO.class).readValue(contentString);
        } catch (IOException e) {
            logger.error("Can not decode the jwt token: {}", token);
            throw new SecurityException(e, ExceptionCode.SYSTEM_ERROR);
        }
    }
}
