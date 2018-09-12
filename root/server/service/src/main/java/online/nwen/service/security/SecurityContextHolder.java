package online.nwen.service.security;

import online.nwen.domain.Author;

/**
 * Security context holder
 */
public class SecurityContextHolder {
    public static final SecurityContextHolder INSTANCE = new SecurityContextHolder();
    private static final ThreadLocal<SecurityContext> CONTEXT_CONTAINER = new ThreadLocal<>();

    private SecurityContextHolder() {
    }

    public void initContext(Author currentAuthor, String jwtToken) {
        SecurityContext context = new SecurityContext(currentAuthor, jwtToken);
        CONTEXT_CONTAINER.set(context);
    }

    public SecurityContext getContext() {
        return CONTEXT_CONTAINER.get();
    }

    public void clearContext() {
        CONTEXT_CONTAINER.remove();
    }
}
