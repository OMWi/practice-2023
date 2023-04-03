package org.example.model;

import java.time.LocalDateTime;

public class Log {
    int id;
    User user;
    LogType type;
    LocalDateTime createdAt = LocalDateTime.now();

    public Log(User user, LogType type) {
        this.user = user;
        this.type = type;
    }
}
