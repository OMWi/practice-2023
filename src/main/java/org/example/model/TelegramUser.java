package org.example.model;

public class TelegramUser {
    int chatId;
    String username;
    boolean isConfirmed = false;

    public TelegramUser(int chatId, String username) {
        this.chatId = chatId;
        this.username = username;
    }
}
