package online.nwen.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

@Document(collection = "comments")
public class Comment implements Serializable {
    private static final long serialVersionUID = -8397645527218208099L;

    public enum Type {
        ARTICLE, ANTHOLOGY
    }

    @Id
    private String id;
    private String authorId;
    private Date createDate;
    private Date updateDate;
    private String content;
    private String parentCommentId;
    private String refDocumentId;
    private Type type;

    public Comment() {
        this.createDate = new Date();
        this.updateDate = this.createDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getParentCommentId() {
        return parentCommentId;
    }

    public void setParentCommentId(String parentCommentId) {
        this.parentCommentId = parentCommentId;
    }

    public String getRefDocumentId() {
        return refDocumentId;
    }

    public void setRefDocumentId(String refDocumentId) {
        this.refDocumentId = refDocumentId;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
