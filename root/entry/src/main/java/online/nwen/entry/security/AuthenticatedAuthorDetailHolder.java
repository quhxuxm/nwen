package online.nwen.entry.security;

import online.nwen.service.dto.AuthorDetailDTO;

/**
 * The thread local used to hold the authenticated author detail
 */
public class AuthenticatedAuthorDetailHolder {
    private static final ThreadLocal<AuthorDetailDTO> HOLDER = new ThreadLocal<>();
    public static AuthenticatedAuthorDetailHolder INSTANCE = new AuthenticatedAuthorDetailHolder();

    private AuthenticatedAuthorDetailHolder() {
    }

    /**
     * Get the authenticated author detail
     *
     * @return The authenticated author detail.
     */
    public AuthorDetailDTO get() {
        return HOLDER.get();
    }

    /**
     * Set the authenticated author detail
     *
     * @param authorDetail The authenticated author detail
     */
    public void set(AuthorDetailDTO authorDetail) {
        HOLDER.set(authorDetail);
    }

    public void clear() {
        HOLDER.remove();
    }
}
