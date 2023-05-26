package org.example.dto.userdata;

public class UserDataHaveRowNumberDto {
    private Long userId;
    private String username;
    private int exp;
    private long position;

    public UserDataHaveRowNumberDto() {
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

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public long getPosition() {
        return position;
    }

    public void setPosition(long position) {
        this.position = position;
    }
}
