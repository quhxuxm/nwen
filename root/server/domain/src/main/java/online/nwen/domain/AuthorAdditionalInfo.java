package online.nwen.domain;

import javax.persistence.*;

@Entity
@Table(name = "author_additional_info")
@Cacheable
public class AuthorAdditionalInfo {
    private static final long serialVersionUID = 8070388640184979679L;
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;
    @Column(name = "comment_number")
    private Long commentNumber;
    @Column(name = "follower_number")
    private Long followerNumber;
    @Column(name = "article_number")
    private Long articleNumber;
    @Column(name = "anthology_number")
    private Long anthologyNumber;

    AuthorAdditionalInfo() {
        this.anthologyNumber = 0L;
        this.commentNumber = 0L;
        this.followerNumber = 0L;
        this.articleNumber = 0L;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCommentNumber() {
        return commentNumber;
    }

    public void setCommentNumber(Long commentNumber) {
        this.commentNumber = commentNumber;
    }

    public Long getFollowerNumber() {
        return followerNumber;
    }

    public void setFollowerNumber(Long followerNumber) {
        this.followerNumber = followerNumber;
    }

    public Long getArticleNumber() {
        return articleNumber;
    }

    public void setArticleNumber(Long articleNumber) {
        this.articleNumber = articleNumber;
    }

    public Long getAnthologyNumber() {
        return anthologyNumber;
    }

    public void setAnthologyNumber(Long anthologyNumber) {
        this.anthologyNumber = anthologyNumber;
    }
}
