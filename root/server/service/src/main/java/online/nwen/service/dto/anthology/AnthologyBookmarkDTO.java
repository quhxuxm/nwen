package online.nwen.service.dto.anthology;

import java.io.Serializable;

public class AnthologyBookmarkDTO implements Serializable {
    private Long authorId;
    private Long anthologyId;

    public Long getAuthorId() {
        return authorId;
    }

    public Long getAnthologyId() {
        return anthologyId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public void setAnthologyId(Long anthologyId) {
        this.anthologyId = anthologyId;
    }
}
