package online.nwen.service.dto.author;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class AuthorDetailDTO implements Serializable {
    private Long authorId;
    private String nickName;
    private String token;
    private Date lastLoginDate;
    private Date registerDate;
    private Set<Role.Name> roles;
    private Long authorIconImageId;
    private Long articleNumber;
    private Long commentNumber;
    private Long anthologyNumber;
    private Long followedByNumber;
    private Set<String> tags;
    private Long defaultAnthologyId;

    public AuthorDetailDTO() {
        this.roles = new HashSet<>();
        this.tags = new HashSet<>();
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public Set<Role.Name> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role.Name> roles) {
        this.roles = roles;
    }

    public Long getAuthorIconImageId() {
        return authorIconImageId;
    }

    public void setAuthorIconImageId(Long authorIconImageId) {
        this.authorIconImageId = authorIconImageId;
    }

    public Long getArticleNumber() {
        return articleNumber;
    }

    public void setArticleNumber(Long articleNumber) {
        this.articleNumber = articleNumber;
    }

    public Long getCommentNumber() {
        return commentNumber;
    }

    public void setCommentNumber(Long commentNumber) {
        this.commentNumber = commentNumber;
    }

    public Long getAnthologyNumber() {
        return anthologyNumber;
    }

    public void setAnthologyNumber(Long anthologyNumber) {
        this.anthologyNumber = anthologyNumber;
    }

    public Long getFollowedByNumber() {
        return followedByNumber;
    }

    public void setFollowedByNumber(Long followedByNumber) {
        this.followedByNumber = followedByNumber;
    }

    public Set<String> getTags() {
        return tags;
    }

    public void setTags(Set<String> tags) {
        this.tags = tags;
    }

    public Long getDefaultAnthologyId() {
        return defaultAnthologyId;
    }

    public void setDefaultAnthologyId(Long defaultAnthologyId) {
        this.defaultAnthologyId = defaultAnthologyId;
    }
}
