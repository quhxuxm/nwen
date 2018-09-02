package online.nwen.service.dto.author;

import online.nwen.domain.Role;

import java.util.HashSet;
import java.util.Set;

public class AuthorAuthenticateDTO {
    private Long id;
    private String token;
    private String password;
    private Set<Role.Name> roles;

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

    public Set<Role.Name> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role.Name> roles) {
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
