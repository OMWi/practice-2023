package org.example.entity;

public class TelegramAccount {
    private int userId;
    private long chatId;
    private String username;
    private boolean isConfirmed = false;

    public TelegramAccount(long chatId, String username) {
        this.chatId = chatId;
        this.username = username;
    }

    public TelegramAccount(int userId, long chatId, String username, boolean isConfirmed) {
        this.userId = userId;
        this.chatId = chatId;
        this.username = username;
        this.isConfirmed = isConfirmed;
    }

    public long getChatId() {
        return chatId;
    }

    public void setChatId(long chatId) {
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "TelegramAccount{" +
                "userId=" + userId +
                ", chatId=" + chatId +
                ", username='" + username + '\'' +
                ", isConfirmed=" + isConfirmed +
                '}';
    }
}
