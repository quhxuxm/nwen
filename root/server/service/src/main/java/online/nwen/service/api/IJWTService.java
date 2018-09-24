package online.nwen.service.api;

import online.nwen.service.dto.security.JwtCreateResultDTO;
import online.nwen.service.dto.security.JwtContentDTO;

public interface IJWTService {
    JwtCreateResultDTO create(JwtContentDTO content, long expiration, long refreshExpiration);

    void verify(String token);

    JwtContentDTO parse(String token);
}
