package online.nwen.service.dto.article;

import java.io.Serializable;
import java.util.Set;

public class ArticleAssignTagsDTO implements Serializable {
    private Long authorId;
    private Long articleId;
    private Set<String> tags;

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public Set<String> getTags() {
        return tags;
    }

    public void setTags(Set<String> tags) {
        this.tags = tags;
    }
}
