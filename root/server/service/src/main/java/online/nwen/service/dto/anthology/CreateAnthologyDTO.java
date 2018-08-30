package online.nwen.service.dto.anthology;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class CreateAnthologyDTO implements Serializable {
    private String title;
    private String summary;
    private Long coverImageId;
    private Set<String> tags;
    private Long authorId;
    private Boolean isPublished;
    private Boolean isShared;

    public CreateAnthologyDTO() {
        this.tags = new HashSet<>();
        this.isPublished = false;
        this.isShared = false;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Long getCoverImageId() {
        return coverImageId;
    }

    public void setCoverImageId(Long coverImageId) {
        this.coverImageId = coverImageId;
    }

    public Set<String> getTags() {
        return tags;
    }

    public void setTags(Set<String> tags) {
        this.tags = tags;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public Boolean getPublished() {
        return isPublished;
    }

    public void setPublished(Boolean published) {
        isPublished = published;
    }

    public Boolean getShared() {
        return isShared;
    }

    public void setShared(Boolean shared) {
        isShared = shared;
    }
}
