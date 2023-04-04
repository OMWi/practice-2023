package org.example.models;

import org.example.enums.UserRole;

public class User {
    private int id;
    private String email;
    private String hashed;
    private String salt;
    private UserRole role = UserRole.USER;

    public User() {
    }

    public User(String email, String hashed, String salt) {
        this.email = email;
        this.hashed = hashed;
        this.salt = salt;
    }

    public User(int id, String email, String hashed, String salt, UserRole role) {
        this.id = id;
        this.email = email;
        this.hashed = hashed;
        this.salt = salt;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHashed() {
        return hashed;
    }

    public void setHashed(String hashed) {
        this.hashed = hashed;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
}
