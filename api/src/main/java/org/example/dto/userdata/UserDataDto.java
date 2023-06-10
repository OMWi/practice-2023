package org.example.dto.userdata;

public class UserDataDto {
    private Long userId;
    private String username;
    private int exp;
    private Boolean isSubscriber;

    public UserDataDto() {
    }

    public UserDataDto(Long userId, String username, int exp, Boolean isSubscriber) {
        this.userId = userId;
        this.username = username;
        this.exp = exp;
        this.isSubscriber = isSubscriber;
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

    public Boolean getIsSubscriber() {
        return isSubscriber;
    }

    public void setIsSubscriber(Boolean isSubscriber) {
        this.isSubscriber = isSubscriber;
    }
}
