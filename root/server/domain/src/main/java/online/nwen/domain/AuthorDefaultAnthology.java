package online.nwen.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "author_default_anthology")
@Cacheable
public class AuthorDefaultAnthology implements Serializable {
    private static final long serialVersionUID = -5512420538467327316L;

    public static class PK implements Serializable {
        private static final long serialVersionUID = -5512420538467327317L;
        @OneToOne
        @JoinColumn(name = "author_id", referencedColumnName = "id",
                updatable = false)
        private Author author;
        @OneToOne
        @JoinColumn(name = "anthology_id", referencedColumnName = "id")
        private Anthology anthology;

        public Author getAuthor() {
            return author;
        }

        public void setAuthor(Author author) {
            this.author = author;
        }

        public Anthology getAnthology() {
            return anthology;
        }

        public void setAnthology(Anthology anthology) {
            this.anthology = anthology;
        }
    }

    @EmbeddedId
    private PK pk;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "mark_date", nullable = false)
    private Date markDate;

    public AuthorDefaultAnthology() {
        this.markDate = new Date();
    }

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
