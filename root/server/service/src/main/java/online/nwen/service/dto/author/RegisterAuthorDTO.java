package online.nwen.service.dto.author;

import java.io.Serializable;

public class RegisterAuthorDTO implements Serializable {
    private String token;
    private String password;
    private String nickname;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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
}
