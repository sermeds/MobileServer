package MobileServer.models;

import MobileServer.models.enums.Role;
import MobileServer.models.enums.Sex;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.*;

@Entity
@Data
@Table(name = "users")
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(of = {"id"})
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "login", unique = true)
    private String login;
    @Column(name = "password", length = 1000)
    private String password;
    @Column(name = "phone")
    private String phone;
    @Column(name = "active")
    private boolean active;

    @Column(name = "firstname")
    @ToString.Include
    private String firstname;
    @Column(name = "lastname")
    @ToString.Include
    private String lastname;
    @Column(name = "birthday")
    @ToString.Include
    private LocalDateTime birthday;
    @Column(name = "sex")
    @ToString.Include
    private Sex sex;

    @Column(name = "balance")
    @ToString.Include
    private int balance;
    @Column(name = "level")
    @ToString.Include
    private int level;
    @Column(name = "exp")
    @ToString.Include
    private int exp;

    @ManyToMany(targetEntity = Achievement.class)
    @Enumerated(EnumType.STRING)
    private Set<Achievement> achievements;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles = new HashSet<>();

    @ToString.Include
    private LocalDateTime dateOfCreated;

    @OneToMany(mappedBy = "user")
    Set<TestRating> ratings;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "user_id")
    private List<Tip> tips = new ArrayList<>();

    @PrePersist
    private void init() {
        dateOfCreated = LocalDateTime.now();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }

}

