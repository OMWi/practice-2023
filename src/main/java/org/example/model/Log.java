package org.example.model;

import jakarta.persistence.*;
import org.example.enums.LogType;

import java.time.LocalDateTime;

@Entity
@Table(name = "log")
public class Log {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LogType type;

    @Column(nullable = false, name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    public Log() {
    }

    public Log(User user, LogType type) {
        this.user = user;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Log{" +
                "id=" + id +
                ", user=" + user +
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
