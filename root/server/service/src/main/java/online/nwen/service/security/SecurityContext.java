package online.nwen.service.security;

/**
 * The security context which used to hold the current author.
 */
public class SecurityContext {
    private String jwtToken;

    SecurityContext(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public String getJwtToken() {
        return jwtToken;
    }
}
