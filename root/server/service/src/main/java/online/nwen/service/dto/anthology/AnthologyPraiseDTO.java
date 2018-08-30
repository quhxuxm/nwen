package online.nwen.service.dto.anthology;

import java.io.Serializable;

public class AnthologyPraiseDTO implements Serializable {
    private Long authorId;
    private Long anthologyId;

    public Long getAnthologyId() {
        return anthologyId;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAnthologyId(Long anthologyId) {
        this.anthologyId = anthologyId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }
}
