package online.nwen.entry.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@ConfigurationProperties(prefix = "api")
@Component
public class ApiConfiguration {
    private Pattern nicknamePattern;
    private Pattern passwordPattern;
    private Pattern usernamePattern;

    public Pattern getNicknamePattern() {
        return nicknamePattern;
    }

    public void setNicknamePattern(Pattern nicknamePattern) {
        this.nicknamePattern = nicknamePattern;
    }

    public Pattern getPasswordPattern() {
        return passwordPattern;
    }

    public void setPasswordPattern(Pattern passwordPattern) {
        this.passwordPattern = passwordPattern;
    }

    public Pattern getUsernamePattern() {
        return usernamePattern;
    }

    public void setUsernamePattern(Pattern usernamePattern) {
        this.usernamePattern = usernamePattern;
    }
}
