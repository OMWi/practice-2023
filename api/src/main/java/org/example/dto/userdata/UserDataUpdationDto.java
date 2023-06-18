package org.example.dto.userdata;

public class UserDataUpdationDto {
    private Long userId;
    private String username;

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

}
