package online.nwen.service.dto.article;

import java.io.Serializable;

public class ViewArticleDTO implements Serializable {
    private Long articleId;

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }
}
