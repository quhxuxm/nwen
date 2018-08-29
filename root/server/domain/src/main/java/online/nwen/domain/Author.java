package online.nwen.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "author")
@Cacheable
public class Author implements Serializable {
    private static final long serialVersionUID = -2652995801468036436L;
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "icon_image_id", referencedColumnName = "id")
    private Resource iconImage;
    @Column(name = "description")
    private String description;
    @Column(name = "nick_name", nullable = false, unique = true, length = 64)
    private String nickName;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(name = "author_role", joinColumns = {
            @JoinColumn(name = "author_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id",
                    referencedColumnName = "id")})
    private Set<Role> roles;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "additional_info_id", referencedColumnName = "id")
    private AuthorAdditionalInfo additionalInfo;
    @Column(name = "token", nullable = false, updatable = false, unique = true)
    private String token;
    @Column(name = "password", nullable = false)
    private String password;
    @Temporal(TemporalType.DATE)
    @Column(name = "register_date", nullable = false, updatable = false)
    private Date registerDate;
    @Temporal(TemporalType.DATE)
    @Column(name = "last_login_date")
    private Date lastLoginDate;

    public Author() {
        this.additionalInfo = new AuthorAdditionalInfo();
        this.registerDate = new Date();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Resource getIconImage() {
        return iconImage;
    }

    public void setIconImage(Resource iconImage) {
        this.iconImage = iconImage;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public AuthorAdditionalInfo getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(AuthorAdditionalInfo additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }
}
