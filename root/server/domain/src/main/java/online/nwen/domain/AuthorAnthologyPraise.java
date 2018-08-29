package online.nwen.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "author_anthology_praise")
public class AuthorAnthologyPraise {
    private static final long serialVersionUID = 3118871567859073683L;

    public static class PK implements Serializable {
        private static final long serialVersionUID = 3118871567859073684L;
        @ManyToOne
        @JoinColumn(name = "author_id", referencedColumnName = "id",
                updatable = false)
        private Author author;
        @ManyToOne
        @JoinColumn(name = "anthology_id", referencedColumnName = "id",
                updatable = false)
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
    private AuthorAnthologyPraise.PK pk;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "praise_date", nullable = false, updatable = false)
    private Date praiseDate;

    public AuthorAnthologyPraise() {
        this.praiseDate = new Date();
    }

    public AuthorAnthologyPraise.PK getPk() {
        return pk;
    }

    public void setPk(AuthorAnthologyPraise.PK pk) {
        this.pk = pk;
    }

    public Date getPraiseDate() {
        return praiseDate;
    }

    public void setPraiseDate(Date praiseDate) {
        this.praiseDate = praiseDate;
    }
}
