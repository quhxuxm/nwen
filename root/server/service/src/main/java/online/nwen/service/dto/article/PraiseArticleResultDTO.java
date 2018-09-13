package online.nwen.service.dto.article;

import java.io.Serializable;

public class PraiseArticleResultDTO implements Serializable {
    private Long praiseNumber;
    private String articleId;

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public Long getPraiseNumber() {
        return praiseNumber;
    }

    public void setPraiseNumber(Long praiseNumber) {
        this.praiseNumber = praiseNumber;
    }
}
