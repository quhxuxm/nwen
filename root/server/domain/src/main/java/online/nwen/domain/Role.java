package online.nwen.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "role")
@Cacheable
public class Role implements Serializable {
    private static final long serialVersionUID = 7799929249915034512L;

    public enum Name {
        ROLE_AUTHOR
    }

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;
    @Column(name = "name", unique = true, nullable = false, length = 128)
    @Enumerated(EnumType.STRING)
    private Name name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }
}
