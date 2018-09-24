package online.nwen.entry.interceptor;

import online.nwen.entry.common.IEntryConstant;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The interceptor used to parse the jwt token from http header and set as a request attribute.
 */
@Component
public class ParseJwtInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String jwtToken = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (jwtToken == null) {
            return true;
        }
        if (jwtToken.indexOf(IEntryConstant.AUTHORIZATION_HEADER_BEARER_PREFIX) > 0) {
            jwtToken = jwtToken.substring(IEntryConstant.AUTHORIZATION_HEADER_BEARER_PREFIX.length() + 1);
        }
        request.setAttribute(IEntryConstant.JWT_TOKEN_REQUEST_ATTR_NAME, jwtToken);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) {
        request.removeAttribute(IEntryConstant.JWT_TOKEN_REQUEST_ATTR_NAME);
    }
}
