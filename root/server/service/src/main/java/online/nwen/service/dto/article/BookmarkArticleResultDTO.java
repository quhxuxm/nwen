package online.nwen.service.dto.article;

import java.io.Serializable;

public class BookmarkArticleResultDTO implements Serializable {
    private Long bookmarkNumber;
    private String articleId;

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public Long getBookmarkNumber() {
        return bookmarkNumber;
    }

    public void setBookmarkNumber(Long bookmarkNumber) {
        this.bookmarkNumber = bookmarkNumber;
    }
}
