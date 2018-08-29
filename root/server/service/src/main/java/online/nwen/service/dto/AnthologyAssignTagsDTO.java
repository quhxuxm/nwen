package online.nwen.service.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class AnthologyAssignTagsDTO implements Serializable {
    private Long anthologyId;
    private Long authorId;
    private Set<String> tags;

    public AnthologyAssignTagsDTO() {
        this.tags = new HashSet<>();
    }

    public Long getAnthologyId() {
        return anthologyId;
    }

    public void setAnthologyId(Long anthologyId) {
        this.anthologyId = anthologyId;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public Set<String> getTags() {
        return tags;
    }

    public void setTags(Set<String> tags) {
        this.tags = tags;
    }
}
