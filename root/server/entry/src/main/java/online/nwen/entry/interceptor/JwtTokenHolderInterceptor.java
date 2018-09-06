package online.nwen.entry.interceptor;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import online.nwen.service.configuration.JwtConfiguration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The interceptor used to fill the thread local that contains the authenticated author detail
 */
@Component
public class JwtTokenHolderInterceptor implements HandlerInterceptor {
    private JwtConfiguration jwtConfiguration;
    private JWTVerifier verifier;

    public JwtTokenHolderInterceptor(JwtConfiguration jwtConfiguration) {
        this.jwtConfiguration = jwtConfiguration;
        Algorithm algorithm = Algorithm.HMAC256(jwtConfiguration.getSecret());
        this.verifier = JWT.require(algorithm)
                .withIssuer(jwtConfiguration.getIssuer())
                .build();
    }

    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler,
                                Exception ex) {
    }
}
