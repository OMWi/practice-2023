package org.example.dto.userdata;

public class UserDataCreationDto {
    private Long userId;
    private String username;

    public UserDataCreationDto() {
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
