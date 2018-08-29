package online.nwen.entry.interceptor;

import online.nwen.entry.security.AuthenticatedAuthorDetailHolder;
import online.nwen.service.dto.AuthorDetailDTO;
import online.nwen.service.impl.common.ICommonConstant;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * The interceptor used to fill the thread local that contains the authenticated author detail
 */
@Component
public class InitializeAuthenticatedAuthorDetailHolderInterceptor implements HandlerInterceptor {
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        HttpSession httpSession = request.getSession();
        AuthorDetailDTO authorDetailDTOFromSession = (AuthorDetailDTO) httpSession
                .getAttribute(
                        ICommonConstant.SessionAttrName.AUTHENTICATED_AUTHOR_DETAIL);
        if (authorDetailDTOFromSession == null) {
            return true;
        }
        AuthenticatedAuthorDetailHolder.INSTANCE
                .set(authorDetailDTOFromSession);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler,
                                Exception ex) {
        AuthenticatedAuthorDetailHolder.INSTANCE.clear();
    }
}
