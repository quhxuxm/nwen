package online.nwen.service.security;

import online.nwen.domain.Author;

/**
 * The security context which used to hold the current author.
 */
public class SecurityContext {
    private Author currentAuthor;
    private String jwtToken;

    SecurityContext(Author currentAuthor, String jwtToken) {
        this.currentAuthor = currentAuthor;
        this.jwtToken = jwtToken;
    }

    public Author getCurrentAuthor() {
        return currentAuthor;
    }

    public String getJwtToken() {
        return jwtToken;
    }
}
