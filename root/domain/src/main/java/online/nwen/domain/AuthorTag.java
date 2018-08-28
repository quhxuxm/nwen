package online.nwen.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "author_tag")
@Cacheable
public class AuthorTag implements Serializable {
    private static final long serialVersionUID = -4652674514830940378L;

    public static class PK implements Serializable {
        private static final long serialVersionUID = -4652674514830940379L;
        @ManyToOne
        @JoinColumn(name = "author_id", referencedColumnName = "id",
                nullable = false, updatable = false)
        private Author author;
        @ManyToOne
        @JoinColumn(name = "tag_id", referencedColumnName = "id",
                nullable = false, updatable = false)
        private Tag tag;

        public Author getAuthor() {
            return author;
        }

        public void setAuthor(Author author) {
            this.author = author;
        }

        public Tag getTag() {
            return tag;
        }

        public void setTag(Tag tag) {
            this.tag = tag;
        }
    }

    @EmbeddedId
    private PK pk;
    @Column(name = "is_selected", nullable = false, updatable = false)
    private Boolean isSelected;
    @Column(name = "weight", nullable = false)
    private Long weight;

    public AuthorTag() {
        this.isSelected = true;
        this.weight = 0L;
    }

    public PK getPk() {
        return pk;
    }

    public void setPk(PK pk) {
        this.pk = pk;
    }

    public Boolean getSelected() {
        return isSelected;
    }

    public void setSelected(Boolean selected) {
        this.isSelected = selected;
    }

    public Long getWeight() {
        return weight;
    }

    public void setWeight(Long weight) {
        this.weight = weight;
    }
}
