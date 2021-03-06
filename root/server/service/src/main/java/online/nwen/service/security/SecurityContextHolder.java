package online.nwen.service.security;

import online.nwen.service.api.ISecurityContext;

/**
 * Security context holder
 */
public class SecurityContextHolder {
    /**
     * The singleton instance.
     */
    public static final SecurityContextHolder INSTANCE = new SecurityContextHolder();
    private static final ThreadLocal<ISecurityContext> CONTEXT_CONTAINER = new ThreadLocal<>();

    private SecurityContextHolder() {
    }

    /**
     * Initialize the security context with a jwt token
     *
     * @param jwtToken The jwt token attached to the security context
     */
    public void initContext(String jwtToken) {
        SecurityContext context = new SecurityContext(jwtToken);
        CONTEXT_CONTAINER.set(context);
    }

    /**
     * Get the security context
     *
     * @return Security context
     */
    public ISecurityContext getContext() {
        return CONTEXT_CONTAINER.get();
    }

    /**
     * Clear the security context
     */
    public void clearContext() {
        CONTEXT_CONTAINER.remove();
    }
}
