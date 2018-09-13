package online.nwen.entry.interceptor;

import online.nwen.entry.common.IEntryConstant;
import online.nwen.service.security.SecurityContextHolder;
import org.springframework.http.HttpHeaders;
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
        String jwtToken = request.getHeader(HttpHeaders.AUTHORIZATION);
        SecurityContextHolder.INSTANCE.initContext(jwtToken);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler,
                                Exception ex) {
        request.removeAttribute(IEntryConstant.RequestAttrName.VERIFIED_JWT_TOKEN);
        SecurityContextHolder.INSTANCE.clearContext();
    }
}
