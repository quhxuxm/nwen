package online.nwen.service.dto.article;

import java.io.Serializable;

public class ViewArticleDTO implements Serializable {
    private String articleId;

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }
}
