package online.nwen.service.security;

import online.nwen.domain.Author;
import online.nwen.service.api.ISecurityContext;

/**
 * The security context which used to hold the current author.
 */
class SecurityContext implements ISecurityContext {
    private Author author;
    private String jwtToken;

    SecurityContext(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    String getJwtToken() {
        return this.jwtToken;
    }

    void setAuthor(Author author) {
        this.author = author;
    }

    public Author getAuthor() {
        return author;
    }
}
