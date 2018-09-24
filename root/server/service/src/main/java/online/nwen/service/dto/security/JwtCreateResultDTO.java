package online.nwen.service.dto.security;

public class JwtCreateResultDTO {
    private String token;
    private long expiration;
    private long refreshExpiration;

    public JwtCreateResultDTO(String token, long expiration, long refreshExpiration) {
        this.token = token;
        this.expiration = expiration;
        this.refreshExpiration = refreshExpiration;
    }

    public String getToken() {
        return token;
    }

    public long getExpiration() {
        return expiration;
    }

    public long getRefreshExpiration() {
        return refreshExpiration;
    }
}