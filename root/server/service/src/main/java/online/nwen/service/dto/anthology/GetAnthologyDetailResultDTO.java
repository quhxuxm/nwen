package online.nwen.service.dto.anthology;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class GetAnthologyDetailResultDTO implements Serializable {
    private String anthologyId;
    private String title;
    private String summary;
    private Date createDate;
    private Date updateDate;
    private Long praisesNumber;
    private Long bookmarksNumber;
    private Long commentsNumber;
    private String coverImageId;
    private String authorId;
    private String authorNickName;
    private String authorIconImageId;
    private Long articleNumber;
    private Set<String> tags;

    public GetAnthologyDetailResultDTO() {
        this.tags = new HashSet<>();
    }

    public String getAnthologyId() {
        return anthologyId;
    }

    public void setAnthologyId(String anthologyId) {
        this.anthologyId = anthologyId;
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

    public Long getPraisesNumber() {
        return praisesNumber;
    }

    public void setPraisesNumber(Long praisesNumber) {
        this.praisesNumber = praisesNumber;
    }

    public Long getBookmarksNumber() {
        return bookmarksNumber;
    }

    public void setBookmarksNumber(Long bookmarksNumber) {
        this.bookmarksNumber = bookmarksNumber;
    }

    public Long getCommentsNumber() {
        return commentsNumber;
    }

    public void setCommentsNumber(Long commentsNumber) {
        this.commentsNumber = commentsNumber;
    }

    public String getCoverImageId() {
        return coverImageId;
    }

    public void setCoverImageId(String coverImageId) {
        this.coverImageId = coverImageId;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getAuthorNickName() {
        return authorNickName;
    }

    public void setAuthorNickName(String authorNickName) {
        this.authorNickName = authorNickName;
    }

    public String getAuthorIconImageId() {
        return authorIconImageId;
    }

    public void setAuthorIconImageId(String authorIconImageId) {
        this.authorIconImageId = authorIconImageId;
    }

    public Long getArticleNumber() {
        return articleNumber;
    }

    public void setArticleNumber(Long articleNumber) {
        this.articleNumber = articleNumber;
    }

    public Set<String> getTags() {
        return tags;
    }
}
