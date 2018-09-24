package online.nwen.entry.interceptor;

import online.nwen.entry.common.IEntryConstant;
import online.nwen.service.security.SecurityContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The interceptor used to fill the thread local that contains the authenticated author detail
 */
@Component
public class InitializeSecurityContextInterceptor implements HandlerInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(InitializeSecurityContextInterceptor.class);

    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        Object jwtTokenAttrValue = request.getAttribute(IEntryConstant.JWT_TOKEN_REQUEST_ATTR_NAME);
        if (jwtTokenAttrValue == null) {
            logger.debug("Can not get jwt token from request attribute, no security context initialized.");
            return true;
        }
        logger.debug("Get jwt token [{}] from request attribute, security context initialized.",
                jwtTokenAttrValue.toString());
        SecurityContextHolder.INSTANCE.initContext(jwtTokenAttrValue.toString());
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler,
                                Exception ex) {
        logger.debug("Clear security context on thread quite.");
        SecurityContextHolder.INSTANCE.clearContext();
    }
}
