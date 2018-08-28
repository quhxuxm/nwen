package online.nwen.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "anthology_participant")
public class AnthologyParticipant implements Serializable {
    private static final long serialVersionUID = -7732733544433259761L;

    public static class PK implements Serializable {
        private static final long serialVersionUID = -7732733544433259762L;
        @ManyToOne(targetEntity = Author.class)
        @JoinColumn(name = "author_id", nullable = false, updatable = false)
        private Author author;
        @ManyToOne(targetEntity = Anthology.class)
        @JoinColumn(name = "anthology_id", nullable = false, updatable = false)
        private Anthology anthology;

        public Anthology getAnthology() {
            return anthology;
        }

        public void setAnthology(Anthology anthology) {
            this.anthology = anthology;
        }

        public Author getAuthor() {
            return author;
        }

        public void setAuthor(Author author) {
            this.author = author;
        }
    }

    @EmbeddedId
    private PK pk;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "participate_date", nullable = false, updatable = false)
    private Date participateDate;
    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted;

    public AnthologyParticipant() {
        this.isDeleted = false;
        this.participateDate = new Date();
    }

    public PK getPk() {
        return pk;
    }

    public void setPk(PK pk) {
        this.pk = pk;
    }

    public Date getParticipateDate() {
        return participateDate;
    }

    public void setParticipateDate(Date participateDate) {
        this.participateDate = participateDate;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        this.isDeleted = deleted;
    }
}
