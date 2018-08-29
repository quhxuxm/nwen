package online.nwen.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "resource")
@Cacheable
public class Resource implements Serializable {
    private static final long serialVersionUID = -7001454343688484844L;
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;
    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(nullable = false, name = "content")
    private byte[] content;
    @Column(nullable = false, length = 40, name = "mime_type")
    private String mimeType;
    @Column(unique = true, nullable = false, length = 128, name = "md5")
    private String md5;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }
}
