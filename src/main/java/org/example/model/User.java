package org.example.model;

import java.util.List;

public class User {
    private int id;
    private String email;
    private String hashed;
    private String salt;
    private String username;
    private TelegramUser telegramUser;
    private List<Word> words;
    private List<WordList> listsOfWords;
    private UserRole role = UserRole.USER;
    private int points = 0;

    public User() {
    }

    public User(String email, String hashed, String salt, String username, TelegramUser telegramUser, List<Word> words, List<WordList> listsOfWords) {
        this.email = email;
        this.hashed = hashed;
        this.salt = salt;
        this.username = username;
        this.telegramUser = telegramUser;
        this.words = words;
        this.listsOfWords = listsOfWords;
    }

    public User(String email, String hashed, String salt, String username, TelegramUser telegramUser, List<Word> words, List<WordList> listsOfWords, UserRole role) {
        this.email = email;
        this.hashed = hashed;
        this.salt = salt;
        this.username = username;
        this.telegramUser = telegramUser;
        this.words = words;
        this.listsOfWords = listsOfWords;
        this.role = role;
    }

    public User(int id, String email, String hashed, String salt, String username, TelegramUser telegramUser, List<Word> words, List<WordList> listsOfWords, UserRole role, int points) {
        this.id = id;
        this.email = email;
        this.hashed = hashed;
        this.salt = salt;
        this.username = username;
        this.telegramUser = telegramUser;
        this.words = words;
        this.listsOfWords = listsOfWords;
        this.role = role;
        this.points = points;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public TelegramUser getTelegramUser() {
        return telegramUser;
    }

    public void setTelegramUser(TelegramUser telegramUser) {
        this.telegramUser = telegramUser;
    }

    public List<Word> getWords() {
        return words;
    }

    public void setWords(List<Word> words) {
        this.words = words;
    }

    public List<WordList> getListsOfWords() {
        return listsOfWords;
    }

    public void setListsOfWords(List<WordList> listsOfWords) {
        this.listsOfWords = listsOfWords;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
