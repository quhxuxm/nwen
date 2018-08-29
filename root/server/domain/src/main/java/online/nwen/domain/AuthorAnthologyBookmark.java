package online.nwen.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "author_anthology_bookmark")
public class AuthorAnthologyBookmark implements Serializable {
    private static final long serialVersionUID = -4674948031108285296L;

    public static class PK implements Serializable {
        @ManyToOne
        @JoinColumn(name = "author_id", updatable = false, nullable = false, referencedColumnName = "id")
        private Author author;
        @ManyToOne
        @JoinColumn(name = "anthology_id", updatable = false, nullable = false, referencedColumnName = "id")
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
    @Column(name = "mark_date", nullable = false, updatable = false)
    private Date markDate;

    public PK getPk() {
        return pk;
    }

    public void setPk(PK pk) {
        this.pk = pk;
    }

    public Date getMarkDate() {
        return markDate;
    }

    public void setMarkDate(Date markDate) {
        this.markDate = markDate;
    }
}
