package org.example.dto.userdata;

public class UserDataUpdationDto {
    private Long userId;
    private String username;
    private int points;
    private Long telegramAccountId;

    public UserDataUpdationDto() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public Long getTelegramAccountId() {
        return telegramAccountId;
    }

    public void setTelegramAccountId(Long telegramAccountId) {
        this.telegramAccountId = telegramAccountId;
    }
}
