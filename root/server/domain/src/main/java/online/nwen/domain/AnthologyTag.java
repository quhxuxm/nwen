package online.nwen.domain;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "anthology_tag")
public class AnthologyTag implements Serializable {
    private static final long serialVersionUID = 8705523091082159065L;

    public static class PK implements Serializable {
        private static final long serialVersionUID = 8705523091082159066L;
        @ManyToOne
        @JoinColumn(name = "anthology_id", nullable = false,
                referencedColumnName = "id", updatable = false)
        private Anthology anthology;
        @ManyToOne
        @JoinColumn(name = "tag_id", nullable = false,
                referencedColumnName = "id", updatable = false)
        private Tag tag;

        public Anthology getAnthology() {
            return anthology;
        }

        public void setAnthology(Anthology anthology) {
            this.anthology = anthology;
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

    public AnthologyTag() {
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
