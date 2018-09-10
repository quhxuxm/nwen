package online.nwen.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.*;

@Document(collection = "authors")
public class Author implements Serializable {
    private static final long serialVersionUID = -2652995801468036436L;
    @Id
    private String id;
    @Indexed(unique = true)
    private String token;
    private String password;
    private String iconImageId;
    private String description;
    @Indexed(unique = true)
    private String nickName;
    private Set<String> roles;
    private Date registerDate;
    private Date lastLoginDate;
    private Set<String> tags;
    private Map<String, Date> followers;
    private String defaultAnthologyId;

    public Author() {
        this.registerDate = new Date();
        this.tags = new HashSet<>();
        this.followers = new HashMap<>();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getIconImageId() {
        return iconImageId;
    }

    public void setIconImageId(String iconImageId) {
        this.iconImageId = iconImageId;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public Map<String, Date> getFollowers() {
        return followers;
    }

    public void setFollowers(Map<String, Date> followers) {
        this.followers = followers;
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

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public Set<String> getTags() {
        return tags;
    }

    public void setTags(Set<String> tags) {
        this.tags = tags;
    }

    public String getDefaultAnthologyId() {
        return defaultAnthologyId;
    }

    public void setDefaultAnthologyId(String defaultAnthologyId) {
        this.defaultAnthologyId = defaultAnthologyId;
    }
}
