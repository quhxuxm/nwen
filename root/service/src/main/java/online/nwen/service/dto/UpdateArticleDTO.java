package online.nwen.service.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class UpdateArticleDTO implements Serializable {
    private Long authorId;
    private Long articleId;
    private String title;
    private String content;
    private String summary;
    private Set<String> tags;
    private boolean isPublished;

    public UpdateArticleDTO() {
        this.tags = new HashSet<>();
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Set<String> getTags() {
        return tags;
    }

    public void setTags(Set<String> tags) {
        this.tags = tags;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public void setPublished(boolean published) {
        isPublished = published;
    }

    public boolean isPublished() {
        return isPublished;
    }
}

