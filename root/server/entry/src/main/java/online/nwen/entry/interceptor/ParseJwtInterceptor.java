package online.nwen.entry.interceptor;

import online.nwen.entry.common.IEntryConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger logger = LoggerFactory.getLogger(ParseJwtInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String jwtToken = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (jwtToken == null) {
            logger.debug("Can not get jwt token from http request header.");
            return true;
        }
        logger.debug("Get jwt token [{}] from http request header.", jwtToken);
        if (jwtToken.indexOf(IEntryConstant.AUTHORIZATION_HEADER_BEARER_PREFIX) > 0) {
            logger.debug("Find prefix [{}] from jwt token [{}]", IEntryConstant.AUTHORIZATION_HEADER_BEARER_PREFIX,
                    jwtToken);
            jwtToken = jwtToken.substring(IEntryConstant.AUTHORIZATION_HEADER_BEARER_PREFIX.length() + 1);
            logger.debug("After remove the prefix, the jwt token is [{}].", jwtToken);
        }
        logger.debug("Put jwt token [{}] to request attribute.", jwtToken);
        request.setAttribute(IEntryConstant.JWT_TOKEN_REQUEST_ATTR_NAME, jwtToken);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) {
        logger.debug("Remove jwt token from request attribute after thread quite.");
        request.removeAttribute(IEntryConstant.JWT_TOKEN_REQUEST_ATTR_NAME);
    }
}
