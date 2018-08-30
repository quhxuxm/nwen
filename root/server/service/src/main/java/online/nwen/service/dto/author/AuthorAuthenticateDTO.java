package online.nwen.service.dto.author;

import java.util.HashSet;
import java.util.Set;

public class AuthorAuthenticateDTO {
    private Long id;
    private String token;
    private String password;
    private Set<String> roles;

    public AuthorAuthenticateDTO() {
        this.roles = new HashSet<>();
    }

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

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
