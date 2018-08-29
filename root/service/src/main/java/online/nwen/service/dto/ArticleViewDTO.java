package online.nwen.service.dto;

import java.io.Serializable;

public class ArticleViewDTO implements Serializable {
    private Long articleId;
    private Long authorId;

    public Long getArticleId() {
        return articleId;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }
}
