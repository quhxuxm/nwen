package online.nwen.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "author_article_bookmark")
public class AuthorArticleBookmark implements Serializable {
    private static final long serialVersionUID = -4774948031108285296L;

    public static class PK implements Serializable {
        private static final long serialVersionUID = -4774948031108285297L;
        @ManyToOne
        @JoinColumn(name = "author_id", updatable = false, nullable = false, referencedColumnName = "id")
        private Author author;
        @ManyToOne
        @JoinColumn(name = "article_id", updatable = false, nullable = false, referencedColumnName = "id")
        private Article article;

        public Article getArticle() {
            return article;
        }

        public void setArticle(Article article) {
            this.article = article;
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
