package online.nwen.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "anthology")
@Cacheable
public class Anthology implements Serializable {
    private static final long serialVersionUID = -190322673132950827L;
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "summary")
    private String summary;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date", nullable = false, updatable = false)
    private Date createDate;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_date", nullable = false)
    private Date updateDate;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "publish_date")
    private Date publishDate;
    @Column(name = "shared_date")
    private Date sharedDate;
    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "id",
            nullable = false, updatable = false)
    private Author author;
    @ManyToOne
    @JoinColumn(name = "cover_image_id", referencedColumnName = "id")
    private Resource coverImage;
    @Column(name = "is_published", nullable = false)
    private Boolean isPublished;
    @Column(name = "is_shared", nullable = false)
    private Boolean isShared;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "additional_info_id", referencedColumnName = "id")
    private AnthologyAdditionalInfo additionalInfo;

    public Anthology() {
        this.createDate = new Date();
        this.updateDate = this.createDate;
        this.isPublished = false;
        this.isShared = false;
        this.additionalInfo = new AnthologyAdditionalInfo();
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

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Resource getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(Resource coverImage) {
        this.coverImage = coverImage;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Boolean getPublished() {
        return isPublished;
    }

    public void setPublished(Boolean published) {
        isPublished = published;
    }

    public Boolean getShared() {
        return isShared;
    }

    public void setShared(Boolean shared) {
        isShared = shared;
    }

    public Date getSharedDate() {
        return sharedDate;
    }

    public void setSharedDate(Date sharedDate) {
        this.sharedDate = sharedDate;
    }

    public AnthologyAdditionalInfo getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(AnthologyAdditionalInfo additionalInfo) {
        this.additionalInfo = additionalInfo;
    }
}
