package online.nwen.entry.interceptor;

import online.nwen.entry.common.IEntryConstant;
import online.nwen.service.security.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The interceptor used to fill the thread local that contains the authenticated author detail
 */
@Component
public class SecurityContextInitializeInterceptor implements HandlerInterceptor {
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        Object jwtTokenAttrValue = request.getAttribute(IEntryConstant.JWT_TOKEN_REQUEST_ATTR_NAME);
        if (jwtTokenAttrValue == null) {
            return true;
        }
        SecurityContextHolder.INSTANCE.initContext(jwtTokenAttrValue.toString());
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler,
                                Exception ex) {
        SecurityContextHolder.INSTANCE.clearContext();
    }
}
