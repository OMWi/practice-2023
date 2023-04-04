package org.example.models;

import org.example.enums.LogType;

import java.time.LocalDateTime;

public class Log {
    private int id;
    private User user;
    private LogType type;
    private LocalDateTime createdAt = LocalDateTime.now();

    public Log() {
    }

    public Log(User user, LogType type) {
        this.user = user;
        this.type = type;
    }

    public Log(int id, User user, LogType type, LocalDateTime createdAt) {
        this.id = id;
        this.user = user;
        this.type = type;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
