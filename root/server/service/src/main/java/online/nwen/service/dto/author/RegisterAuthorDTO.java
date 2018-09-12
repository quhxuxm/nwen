package online.nwen.service.dto.author;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class RegisterAuthorDTO implements Serializable {
    private String username;
    private String password;
    private String nickname;
    private Set<String> tags;

    public RegisterAuthorDTO() {
        this.tags = new HashSet<>();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Set<String> getTags() {
        return tags;
    }

    public void setTags(Set<String> tags) {
        this.tags = tags;
    }
}
