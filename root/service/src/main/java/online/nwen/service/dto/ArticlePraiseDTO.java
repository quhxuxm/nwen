package online.nwen.service.dto;

import java.io.Serializable;

public class ArticlePraiseDTO implements Serializable {
    private Long authorId;
    private Long articleId;

    public Long getAuthorId() {
        return authorId;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }
}
