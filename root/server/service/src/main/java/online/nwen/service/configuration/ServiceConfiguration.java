package online.nwen.service.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "service")
@Component
public class ServiceConfiguration {
    private String jwtSecret;
    private String jwtIssuer;
    private long jwtExpiration;
    private long jwtRefreshExpiration;
    private long viewDateInterval;

    public String getJwtSecret() {
        return jwtSecret;
    }

    public void setJwtSecret(String jwtSecret) {
        this.jwtSecret = jwtSecret;
    }

    public String getJwtIssuer() {
        return jwtIssuer;
    }

    public void setJwtIssuer(String jwtIssuer) {
        this.jwtIssuer = jwtIssuer;
    }

    public long getJwtExpiration() {
        return jwtExpiration;
    }

    public void setJwtExpiration(long jwtExpiration) {
        this.jwtExpiration = jwtExpiration;
    }

    public void setJwtRefreshExpiration(long jwtRefreshExpiration) {
        this.jwtRefreshExpiration = jwtRefreshExpiration;
    }

    public long getJwtRefreshExpiration() {
        return jwtRefreshExpiration;
    }

    public long getViewDateInterval() {
        return viewDateInterval;
    }

    public void setViewDateInterval(long viewDateInterval) {
        this.viewDateInterval = viewDateInterval;
    }
}
