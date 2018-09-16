package online.nwen.service.dto.author;

import online.nwen.domain.Role;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class GetAuthorDetailResultDTO implements Serializable {
    private String authorId;
    private String nickName;
    private String token;
    private Date lastLoginDate;
    private Date registerDate;
    private Set<Role> roles;
    private String authorIconImageId;
    private Long articleNumber;
    private Long commentNumber;
    private Long anthologyNumber;
    private Long followerNumber;
    private Set<String> tags;
    private String defaultAnthologyId;

    public GetAuthorDetailResultDTO() {
        this.roles = new HashSet<>();
        this.tags = new HashSet<>();
        this.followerNumber = 0L;
        this.anthologyNumber = 0L;
        this.articleNumber = 0L;
        this.commentNumber = 0L;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
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

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getAuthorIconImageId() {
        return authorIconImageId;
    }

    public void setAuthorIconImageId(String authorIconImageId) {
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

    public Long getFollowerNumber() {
        return followerNumber;
    }

    public void setFollowerNumber(Long followerNumber) {
        this.followerNumber = followerNumber;
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
