package online.nwen.entry.common;

import com.auth0.jwt.interfaces.DecodedJWT;
import online.nwen.service.dto.author.AuthorDetailDTO;

public class SecurityContext {
    private AuthorDetailDTO currentAuthor;
    private DecodedJWT jwt;

    public SecurityContext(AuthorDetailDTO currentAuthor, DecodedJWT jwt) {
        this.currentAuthor = currentAuthor;
        this.jwt = jwt;
    }

    public AuthorDetailDTO getCurrentAuthor() {
        return currentAuthor;
    }

    public DecodedJWT getJwt() {
        return jwt;
    }
}
