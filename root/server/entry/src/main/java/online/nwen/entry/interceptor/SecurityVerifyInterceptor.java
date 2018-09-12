package online.nwen.entry.interceptor;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import online.nwen.entry.common.IEntryConstant;
import online.nwen.service.configuration.JwtConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The interceptor used to fill the thread local that contains the authenticated author detail
 */
@Component
public class SecurityVerifyInterceptor implements HandlerInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(SecurityVerifyInterceptor.class);
    private JWTVerifier verifier;

    public SecurityVerifyInterceptor(JwtConfiguration jwtConfiguration) {
        Algorithm algorithm = Algorithm.HMAC256(jwtConfiguration.getSecret());
        this.verifier = JWT.require(algorithm)
                .withIssuer(jwtConfiguration.getIssuer())
                .build();
    }

    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        String jwtToken = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (jwtToken == null) {
            logger.error("The jwt token is not exist in the request header.");
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            request.removeAttribute(IEntryConstant.RequestAttrName.VERIFIED_JWT_TOKEN);
            return false;
        }
        DecodedJWT currentJwt = null;
        try {
            currentJwt = this.verifier.verify(jwtToken);
        } catch (Exception e) {
            logger.error("Fail to verify the jwt token.", e);
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            request.removeAttribute(IEntryConstant.RequestAttrName.VERIFIED_JWT_TOKEN);
            return false;
        }
        request.setAttribute(IEntryConstant.RequestAttrName.VERIFIED_JWT_TOKEN, currentJwt);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler,
                                Exception ex) {
        request.removeAttribute(IEntryConstant.RequestAttrName.VERIFIED_JWT_TOKEN);
    }
}
