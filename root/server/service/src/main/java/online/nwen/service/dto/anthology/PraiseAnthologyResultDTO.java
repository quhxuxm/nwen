package online.nwen.service.dto.anthology;

import java.io.Serializable;

public class PraiseAnthologyResultDTO implements Serializable {
    private Long praiseNumber;
    private String anthologyId;

    public String getAnthologyId() {
        return anthologyId;
    }

    public void setAnthologyId(String anthologyId) {
        this.anthologyId = anthologyId;
    }

    public Long getPraiseNumber() {
        return praiseNumber;
    }

    public void setPraiseNumber(Long praiseNumber) {
        this.praiseNumber = praiseNumber;
    }
}
