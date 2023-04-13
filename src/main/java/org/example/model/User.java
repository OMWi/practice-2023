package org.example.model;

import jakarta.persistence.*;
import org.example.enums.UserRole;

import java.util.List;

@Entity
@Table(name = "custom_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String hashed;

    @Column(nullable = false)
    private String salt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole role;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private UserData userData;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Log> logs;

    public User() {
    }

    public User(String email, String hashed, String salt, UserRole role) {
        this.email = email;
        this.hashed = hashed;
        this.salt = salt;
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", hashed='" + hashed + '\'' +
                ", salt='" + salt + '\'' +
                ", role=" + role +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHashed() {
        return hashed;
    }

    public void setHashed(String hashed) {
        this.hashed = hashed;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public List<Log> getLogs() {
        return logs;
    }

    public void addLog(Log log) {
        this.logs.add(log);
        log.setUser(this);
    }

    public void removeLog(Log log) {
        log.setUser(null);
        this.logs.remove(log);
    }

    public UserData getUserData() {
        return userData;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }
}
