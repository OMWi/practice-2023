package org.example.dto.userdata;

import java.sql.Date;

public class UserDataDto {
    private Long userId;
    private String username;
    private int exp;
    private Boolean isSubscriber;
    private Date subscriptionExpirationDate;

    public UserDataDto() {
    }

    public UserDataDto(Long userId, String username, int exp, Boolean isSubscriber, Date subscriptionExpirationDate) {
        this.userId = userId;
        this.username = username;
        this.exp = exp;
        this.isSubscriber = isSubscriber;
        this.subscriptionExpirationDate = subscriptionExpirationDate;
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

    public Date getSubscriptionExpirationDate() {
        return subscriptionExpirationDate;
    }

    public void setSubscriptionExpirationDate(Date subscriptionExpirationDate) {
        this.subscriptionExpirationDate = subscriptionExpirationDate;
    }
}
