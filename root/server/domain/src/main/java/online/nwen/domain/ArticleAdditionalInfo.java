package online.nwen.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "article_additional_info")
public class ArticleAdditionalInfo implements Serializable {
    private static final long serialVersionUID = 8070388660184979679L;
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;
    @Column(name = "praise_number")
    private Long praiseNumber;
    @Column(name = "comment_number")
    private Long commentNumber;
    @Column(name = "view_number")
    private Long viewNumber;
    @Column(name = "bookmark_number")
    private Long bookmarkNumber;

    ArticleAdditionalInfo() {
        this.praiseNumber = 0L;
        this.commentNumber = 0L;
        this.bookmarkNumber = 0L;
        this.viewNumber = 0L;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getViewNumber() {
        return viewNumber;
    }

    public void setViewNumber(Long viewNumber) {
        this.viewNumber = viewNumber;
    }

    public Long getBookmarkNumber() {
        return bookmarkNumber;
    }

    public void setBookmarkNumber(Long bookmarkNumber) {
        this.bookmarkNumber = bookmarkNumber;
    }
}
