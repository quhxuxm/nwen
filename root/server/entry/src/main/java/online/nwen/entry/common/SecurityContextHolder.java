package online.nwen.entry.common;

import com.auth0.jwt.interfaces.DecodedJWT;
import online.nwen.service.dto.author.AuthorDetailDTO;

public class SecurityContextHolder {
    public static final SecurityContextHolder INSTANCE = new SecurityContextHolder();
    private static final ThreadLocal<SecurityContext> CONTEXT_CONTAINER = new ThreadLocal<>();

    private SecurityContextHolder() {
    }

    public void initContext(AuthorDetailDTO currentAuthor, DecodedJWT jwt) {
        SecurityContext context = new SecurityContext(currentAuthor, jwt);
        CONTEXT_CONTAINER.set(context);
    }

    public SecurityContext getContext() {
        return CONTEXT_CONTAINER.get();
    }

    public void clearContext() {
        CONTEXT_CONTAINER.remove();
    }
}
