package org.example.dto.userdata;

import org.example.dto.telegramaccount.TelegramAccountDto;

public class UserDataDto {
    private Long userId;
    private String username;
    private int points;
    private TelegramAccountDto telegramAccountDto;

    public UserDataDto() {
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

    public TelegramAccountDto getTelegramAccountDto() {
        return telegramAccountDto;
    }

    public void setTelegramAccountDto(TelegramAccountDto telegramAccountDto) {
        this.telegramAccountDto = telegramAccountDto;
    }
}
