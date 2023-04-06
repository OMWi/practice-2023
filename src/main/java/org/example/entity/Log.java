package org.example.entity;

import org.example.enums.LogType;

import java.time.LocalDateTime;

public class Log {
    private int id;
    private int userId;
    private LogType type;
    private LocalDateTime createdAt = LocalDateTime.now();

    public Log(int userId, LogType type) {
        this.userId = userId;
        this.type = type;
    }

    public Log(int id, int userId, LogType type, LocalDateTime createdAt) {
        this.id = id;
        this.userId = userId;
        this.type = type;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    @Override
    public String toString() {
        return "Log{" +
                "id=" + id +
                ", userId=" + userId +
                ", type=" + type +
                ", createdAt=" + createdAt +
                '}';
    }
}
