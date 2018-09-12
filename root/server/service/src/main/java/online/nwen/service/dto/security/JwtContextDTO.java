package online.nwen.service.dto.security;

public class JwtContextDTO {
    private String jwtToken;
    private long expiration;

    public JwtContextDTO(String jwtToken, long expiration) {
        this.jwtToken = jwtToken;
        this.expiration = expiration;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public long getExpiration() {
        return expiration;
    }
}