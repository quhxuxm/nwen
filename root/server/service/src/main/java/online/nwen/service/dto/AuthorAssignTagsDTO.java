package online.nwen.service.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class AuthorAssignTagsDTO implements Serializable {
    private Long authorId;
    private Set<String> tags;
    private boolean isSelect;

    public AuthorAssignTagsDTO() {
        this.tags = new HashSet<>();
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

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }
}
