package online.nwen.service.dto;

import java.io.Serializable;

public class AuthorAssignFollowerDTO implements Serializable {
    private Long authorId;
    private Long followerId;

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public Long getFollowerId() {
        return followerId;
    }

    public void setFollowerId(Long followerId) {
        this.followerId = followerId;
    }
}
