package org.example.models;

public class TelegramUser {
    private int chatId;
    private String username;
    private boolean isConfirmed = false;

    public TelegramUser() {
    }

    public TelegramUser(int chatId, String username) {
        this.chatId = chatId;
        this.username = username;
    }

    public TelegramUser(int chatId, String username, boolean isConfirmed) {
        this.chatId = chatId;
        this.username = username;
        this.isConfirmed = isConfirmed;
    }

    public int getChatId() {
        return chatId;
    }

    public void setChatId(int chatId) {
        this.chatId = chatId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isConfirmed() {
        return isConfirmed;
    }

    public void setConfirmed(boolean confirmed) {
        isConfirmed = confirmed;
    }
}
