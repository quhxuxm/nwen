package online.nwen.service.dto.anthology;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class SaveAnthologyDTO implements Serializable {
    private String title;
    private String summary;
    private String coverImageId;
    private Set<String> tags;
    private Boolean isPublished;
    private Boolean isShared;
    private String anthologyId;

    public SaveAnthologyDTO() {
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

    public String getCoverImageId() {
        return coverImageId;
    }

    public void setCoverImageId(String coverImageId) {
        this.coverImageId = coverImageId;
    }

    public Set<String> getTags() {
        return tags;
    }

    public void setTags(Set<String> tags) {
        this.tags = tags;
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

    public String getAnthologyId() {
        return anthologyId;
    }

    public void setAnthologyId(String anthologyId) {
        this.anthologyId = anthologyId;
    }
}
