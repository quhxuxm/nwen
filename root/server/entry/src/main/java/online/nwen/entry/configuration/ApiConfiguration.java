package online.nwen.entry.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@ConfigurationProperties(prefix = "api")
@Component
public class ApiConfiguration {
    private Pattern nickNamePattern;
    private Pattern passwordPattern;
    private Pattern tokenPattern;

    public Pattern getNickNamePattern() {
        return nickNamePattern;
    }

    public void setNickNamePattern(Pattern nickNamePattern) {
        this.nickNamePattern = nickNamePattern;
    }

    public Pattern getPasswordPattern() {
        return passwordPattern;
    }

    public void setPasswordPattern(Pattern passwordPattern) {
        this.passwordPattern = passwordPattern;
    }

    public Pattern getTokenPattern() {
        return tokenPattern;
    }

    public void setTokenPattern(Pattern tokenPattern) {
        this.tokenPattern = tokenPattern;
    }
}
