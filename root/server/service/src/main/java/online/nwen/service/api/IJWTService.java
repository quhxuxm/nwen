package online.nwen.service.api;

import online.nwen.domain.Author;
import online.nwen.service.dto.security.JwtContextDTO;

public interface IJWTService {
    JwtContextDTO createJwtTokenWithAuthor(Author author);

    void verify(String jwtToken);

    Author getAuthorFromJwtToken(String jwtToken);
}
