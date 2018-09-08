package online.nwen.entry.common;

import online.nwen.service.dto.author.AuthorDetailDTO;

public class SecurityContext {
    private AuthorDetailDTO currentAuthor;
    private String jwtToken;

    public SecurityContext(AuthorDetailDTO currentAuthor, String jwtToken) {
        this.currentAuthor = currentAuthor;
        this.jwtToken = jwtToken;
    }

    public AuthorDetailDTO getCurrentAuthor() {
        return currentAuthor;
    }

    public String getJwtToken() {
        return jwtToken;
    }
}
