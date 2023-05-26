package org.example.dto.auth;

import org.example.enums.UserRole;

public class JwtDto {
    private String token;
    private String type = "Bearer";
    private Long userId;
    private String email;
    private UserRole role;
    private String username;

    public JwtDto() {
    }

    public JwtDto(String token, Long userId, String email, UserRole role, String username) {
        this.token = token;
        this.userId = userId;
        this.email = email;
        this.role = role;
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
