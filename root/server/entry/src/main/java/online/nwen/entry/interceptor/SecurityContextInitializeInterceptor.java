package online.nwen.entry.interceptor;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import online.nwen.entry.common.IEntryConstant;
import online.nwen.entry.common.SecurityContextHolder;
import online.nwen.service.api.IAuthorService;
import online.nwen.service.dto.author.AuthorDetailDTO;
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
    private IAuthorService authorService;

    public SecurityContextInitializeInterceptor(
            IAuthorService authorService) {
        this.authorService = authorService;
    }

    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        String jwtToken = request.getHeader(HttpHeaders.AUTHORIZATION);
        DecodedJWT verifiedJwt = (DecodedJWT) request.getAttribute(IEntryConstant.RequestAttrName.VERIFIED_JWT_TOKEN);
        if (verifiedJwt != null) {
            AuthorDetailDTO currentAuthor = this.authorService.findDetailById(Long.parseLong(verifiedJwt.getSubject()));
            SecurityContextHolder.INSTANCE.initContext(currentAuthor, jwtToken);
            return true;
        }
        if (jwtToken != null) {
            AuthorDetailDTO currentAuthor =
                    this.authorService.findDetailById(Long.parseLong(JWT.decode(jwtToken).getSubject()));
            SecurityContextHolder.INSTANCE.initContext(currentAuthor, jwtToken);
            return true;
        }
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
