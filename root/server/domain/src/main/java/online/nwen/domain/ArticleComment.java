package online.nwen.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "article_comment")
public class ArticleComment implements Serializable {
    private static final long serialVersionUID = -8397645527218208099L;
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false, updatable = false)
    private Author author;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date", nullable = false, updatable = false)
    private Date createDate;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_date", nullable = false, updatable = false)
    private Date updateDate;
    @Column(name = "content", length = 2000, nullable = false)
    private String content;
    @ManyToOne
    @JoinColumn(name = "parent_id", nullable = false, updatable = false)
    private ArticleComment parent;
    @ManyToOne(targetEntity = Article.class)
    @JoinColumn(name = "article_id", nullable = false, updatable = false)
    private Article article;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public ArticleComment getParent() {
        return parent;
    }

    public void setParent(ArticleComment parent) {
        this.parent = parent;
    }
}
