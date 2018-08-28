package online.nwen.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "article")
@Cacheable
public class Article implements Serializable {
    private static final long serialVersionUID = 8070388670184979679L;
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;
    @Column(name = "title", nullable = false, length = 1000)
    private String title;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "publish_date")
    private Date publishDate;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_date", nullable = false)
    private Date updateDate;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date", nullable = false, updatable = false)
    private Date createDate;
    @Lob
    @Column(name = "content", nullable = false)
    @Basic(fetch = FetchType.LAZY)
    private String content;
    @Column(name = "summary", nullable = false, length = 2000)
    private String summary;
    @ManyToOne
    @JoinColumn(name = "anthology_id", referencedColumnName = "id",
            nullable = false, updatable = false)
    private Anthology anthology;
    @ManyToOne
    @JoinColumn(name = "cover_image_id", referencedColumnName = "id")
    private Resource coverImage;
    @Column(name = "is_published", nullable = false)
    private Boolean isPublished;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "additional_info_id", referencedColumnName = "id")
    private ArticleAdditionalInfo additionalInfo;

    public Article() {
        this.createDate = new Date();
        this.updateDate = this.createDate;
        this.isPublished = false;
        this.additionalInfo = new ArticleAdditionalInfo();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Anthology getAnthology() {
        return anthology;
    }

    public void setAnthology(Anthology anthology) {
        this.anthology = anthology;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Resource getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(Resource coverImage) {
        this.coverImage = coverImage;
    }

    public Boolean getPublished() {
        return isPublished;
    }

    public void setPublished(Boolean published) {
        isPublished = published;
    }

    public ArticleAdditionalInfo getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(ArticleAdditionalInfo additionalInfo) {
        this.additionalInfo = additionalInfo;
    }
}
