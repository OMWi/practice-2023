package org.example.model;

import jakarta.persistence.*;
import org.example.enums.LogType;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "log")
public class Log {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserCredentials userCredentials;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LogType type;

    @CreationTimestamp
    @Column(nullable = false, name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    public Log() {
    }

    public Log(UserCredentials userCredentials, LogType type) {
        this.userCredentials = userCredentials;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Log{" +
                "id=" + id +
                ", user=" + userCredentials +
                ", type=" + type +
                ", createdAt=" + createdAt +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserCredentials getUser() {
        return userCredentials;
    }

    public void setUser(UserCredentials userCredentials) {
        this.userCredentials = userCredentials;
    }

    public LogType getType() {
        return type;
    }

    public void setType(LogType type) {
        this.type = type;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
