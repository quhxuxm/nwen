package online.nwen.service.dto.article;

import java.io.Serializable;
import java.util.Date;

public class ArticleSummaryDTO implements Serializable {
    private Long articleId;
    private String title;
    private String summary;
    private Date createDate;
    private Date updateDate;
    private Long authorId;
    private String authorNickName;
    private Long authorIconImageId;
    private Long anthologyId;
    private String anthologyTitle;
    private Long anthologyCoverImageId;
    private Long praiseNumber;
    private Long bookmarkNumber;
    private Long commentNumber;
    private Long viewNumber;
    private Long coverImageId;

    public ArticleSummaryDTO() {
        this.praiseNumber = 0L;
        this.commentNumber = 0L;
        this.viewNumber = 0L;
        this.bookmarkNumber = 0L;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public String getAuthorNickName() {
        return authorNickName;
    }

    public void setAuthorNickName(String authorNickName) {
        this.authorNickName = authorNickName;
    }

    public Long getAuthorIconImageId() {
        return authorIconImageId;
    }

    public void setAuthorIconImageId(Long authorIconImageId) {
        this.authorIconImageId = authorIconImageId;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Long getAnthologyId() {
        return anthologyId;
    }

    public void setAnthologyId(Long anthologyId) {
        this.anthologyId = anthologyId;
    }

    public String getAnthologyTitle() {
        return anthologyTitle;
    }

    public void setAnthologyTitle(String anthologyTitle) {
        this.anthologyTitle = anthologyTitle;
    }

    public Long getAnthologyCoverImageId() {
        return anthologyCoverImageId;
    }

    public void setAnthologyCoverImageId(Long anthologyCoverImageId) {
        this.anthologyCoverImageId = anthologyCoverImageId;
    }

    public Long getBookmarkNumber() {
        return bookmarkNumber;
    }

    public void setBookmarkNumber(Long bookmarkNumber) {
        this.bookmarkNumber = bookmarkNumber;
    }

    public Long getPraiseNumber() {
        return praiseNumber;
    }

    public void setPraiseNumber(Long praiseNumber) {
        this.praiseNumber = praiseNumber;
    }

    public Long getCommentNumber() {
        return commentNumber;
    }

    public void setCommentNumber(Long commentNumber) {
        this.commentNumber = commentNumber;
    }

    public Long getCoverImageId() {
        return coverImageId;
    }

    public void setCoverImageId(Long coverImageId) {
        this.coverImageId = coverImageId;
    }

    public Long getViewNumber() {
        return viewNumber;
    }

    public void setViewNumber(Long viewNumber) {
        this.viewNumber = viewNumber;
    }
}
