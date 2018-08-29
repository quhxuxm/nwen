package online.nwen.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "anthology_comment")
public class AnthologyComment implements Serializable {
    private static final long serialVersionUID = 883134394638579144L;
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false, updatable = false)
    private Author author;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date", updatable = false, nullable = false)
    private Date createDate;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_date")
    private Date updateDate;
    @Column(name = "content", length = 2000, nullable = false)
    private String content;
    @ManyToOne
    @JoinColumn(name = "parent_id", referencedColumnName = "id", updatable = false)
    private AnthologyComment parent;
    @ManyToOne
    @JoinColumn(name = "anthology_id", referencedColumnName = "id", updatable = false)
    private Anthology anthology;

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

    public AnthologyComment getParent() {
        return parent;
    }

    public void setParent(AnthologyComment parent) {
        this.parent = parent;
    }

    public Anthology getAnthology() {
        return anthology;
    }

    public void setAnthology(Anthology anthology) {
        this.anthology = anthology;
    }
}
