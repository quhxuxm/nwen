package online.nwen.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "author_article_praise")
public class AuthorArticlePraise implements Serializable {
    private static final long serialVersionUID = 3118871567859072683L;

    public static class PK implements Serializable {
        private static final long serialVersionUID = 3118871567859072684L;
        @ManyToOne
        @JoinColumn(name = "author_id", referencedColumnName = "id", updatable = false)
        private Author author;
        @ManyToOne
        @JoinColumn(name = "article_id", referencedColumnName = "id", updatable = false)
        private Article article;

        public Author getAuthor() {
            return author;
        }

        public void setAuthor(Author author) {
            this.author = author;
        }

        public Article getArticle() {
            return article;
        }

        public void setArticle(Article article) {
            this.article = article;
        }
    }

    @EmbeddedId
    private PK pk;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "praise_date", nullable = false, updatable = false)
    private Date praiseDate;

    public AuthorArticlePraise() {
        this.praiseDate = new Date();
    }

    public PK getPk() {
        return pk;
    }

    public void setPk(PK pk) {
        this.pk = pk;
    }

    public Date getPraiseDate() {
        return praiseDate;
    }

    public void setPraiseDate(Date praiseDate) {
        this.praiseDate = praiseDate;
    }
}
