package online.nwen.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "author_follower")
public class AuthorFollower implements Serializable {
    private static final long serialVersionUID = -772343683934230934L;

    public static class PK implements Serializable {
        private static final long serialVersionUID = -772343683934230935L;
        @ManyToOne
        @JoinColumn(name = "author_id", referencedColumnName = "id", nullable = false, updatable = false)
        private Author author;
        @ManyToOne
        @JoinColumn(name = "follower_id", referencedColumnName = "id", nullable = false, updatable = false)
        private Author follower;

        public Author getAuthor() {
            return author;
        }

        public void setAuthor(Author author) {
            this.author = author;
        }

        public Author getFollower() {
            return follower;
        }

        public void setFollower(Author follower) {
            this.follower = follower;
        }
    }

    @EmbeddedId
    private PK pk;
    @Temporal(TemporalType.DATE)
    @Column(name = "follow_date", nullable = false, updatable = false)
    private Date followDate;

    public AuthorFollower() {
        this.followDate = new Date();
    }

    public PK getPk() {
        return pk;
    }

    public void setPk(PK pk) {
        this.pk = pk;
    }

    public Date getFollowDate() {
        return followDate;
    }

    public void setFollowDate(Date followDate) {
        this.followDate = followDate;
    }
}
