package online.nwen.service.dto.security;

import java.util.Set;

public class JwtContentDTO {
    private String authorId;
    private String authorIconImageId;
    private String authorNickname;
    private String authorUsername;
    private String authorDefaultAnthologyId;
    private Set<String> authorTags;

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getAuthorIconImageId() {
        return authorIconImageId;
    }

    public void setAuthorIconImageId(String authorIconImageId) {
        this.authorIconImageId = authorIconImageId;
    }

    public String getAuthorNickname() {
        return authorNickname;
    }

    public void setAuthorNickname(String authorNickname) {
        this.authorNickname = authorNickname;
    }

    public String getAuthorUsername() {
        return authorUsername;
    }

    public void setAuthorUsername(String authorUsername) {
        this.authorUsername = authorUsername;
    }

    public String getAuthorDefaultAnthologyId() {
        return authorDefaultAnthologyId;
    }

    public void setAuthorDefaultAnthologyId(String authorDefaultAnthologyId) {
        this.authorDefaultAnthologyId = authorDefaultAnthologyId;
    }

    public Set<String> getAuthorTags() {
        return authorTags;
    }

    public void setAuthorTags(Set<String> authorTags) {
        this.authorTags = authorTags;
    }
}
