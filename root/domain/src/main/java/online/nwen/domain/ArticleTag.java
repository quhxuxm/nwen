package online.nwen.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "article_tag")
public class ArticleTag implements Serializable {
    private static final long serialVersionUID = 5077971124151612394L;

    public static class PK implements Serializable {
        private static final long serialVersionUID = 5077971124151612395L;
        @ManyToOne
        @JoinColumn(name = "article_id", referencedColumnName = "id", updatable = false, nullable = false)
        private Article article;
        @ManyToOne
        @JoinColumn(name = "tag_id", referencedColumnName = "id", updatable = false, nullable = false)
        private Tag tag;

        public Article getArticle() {
            return article;
        }

        public void setArticle(Article article) {
            this.article = article;
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

    public ArticleTag() {
        this.isSelected = false;
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
