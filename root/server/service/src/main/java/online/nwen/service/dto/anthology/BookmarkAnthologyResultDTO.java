package online.nwen.service.dto.anthology;

import java.io.Serializable;

public class BookmarkAnthologyResultDTO implements Serializable {
    private Long bookmarkNumber;
    private String anthologyId;

    public String getAnthologyId() {
        return anthologyId;
    }

    public void setAnthologyId(String anthologyId) {
        this.anthologyId = anthologyId;
    }

    public Long getBookmarkNumber() {
        return bookmarkNumber;
    }

    public void setBookmarkNumber(Long bookmarkNumber) {
        this.bookmarkNumber = bookmarkNumber;
    }
}
