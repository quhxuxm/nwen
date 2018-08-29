package online.nwen.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "tag")
@Cacheable
public class Tag implements Serializable {
    private static final long serialVersionUID = -5345884376453719900L;
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;
    @Column(name = "text", unique = true, nullable = false, length = 64)
    private String text;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Tag tag = (Tag) o;
        return Objects.equals(id, tag.id) && Objects.equals(text, tag.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, text);
    }
}
