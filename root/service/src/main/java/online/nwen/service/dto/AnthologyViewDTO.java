package online.nwen.service.dto;

import java.io.Serializable;

public class AnthologyViewDTO implements Serializable {
    private Long anthologyId;
    private Long authorId;

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
}
