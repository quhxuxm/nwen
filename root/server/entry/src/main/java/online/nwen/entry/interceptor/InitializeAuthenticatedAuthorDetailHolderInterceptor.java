package online.nwen.entry.interceptor;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * The interceptor used to fill the thread local that contains the authenticated author detail
 */
@Component
public class JwtTokenHolderInterceptor implements HandlerInterceptor {
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        String jwtToken = request.getHeader(HttpHeaders.AUTHORIZATION);
        Jwts
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler,
                                Exception ex) {
        AuthenticatedAuthorDetailHolder.INSTANCE.clear();
    }
}
