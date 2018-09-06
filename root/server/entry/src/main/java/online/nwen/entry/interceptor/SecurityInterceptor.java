package online.nwen.entry.interceptor;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import online.nwen.entry.common.SecurityContextHolder;
import online.nwen.service.api.IAuthorService;
import online.nwen.service.configuration.JwtConfiguration;
import online.nwen.service.dto.author.AuthorDetailDTO;
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
public class SecurityInterceptor implements HandlerInterceptor {
    private JWTVerifier verifier;
    private IAuthorService authorService;

    public SecurityInterceptor(JwtConfiguration jwtConfiguration,
                               IAuthorService authorService) {
        this.authorService = authorService;
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
            return true;
        }
        DecodedJWT currentJwt = null;
        try {
            currentJwt = this.verifier.verify(jwtToken);
        } catch (Exception e) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            SecurityContextHolder.INSTANCE.clearContext();
            return false;
        }
        AuthorDetailDTO currentAuthor = this.authorService.findDetailById(Long.parseLong(currentJwt.getSubject()));
        SecurityContextHolder.INSTANCE.initContext(currentAuthor, currentJwt);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler,
                                Exception ex) {
        SecurityContextHolder.INSTANCE.clearContext();
    }
}
