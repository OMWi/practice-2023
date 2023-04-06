package org.example.entity;

import java.util.Set;

public class UserData {
    private int userId;
    private String username;
    private int points = 0;
    private TelegramAccount telegramAccount;
    private Set<Word> words;
    private Set<WordList> wordLists;

    public UserData(int userId, String username) {
        this.userId = userId;
        this.username = username;
    }

    public UserData(int userId, String username, int points, TelegramAccount telegramAccount, Set<Word> words, Set<WordList> wordLists) {
        this.userId = userId;
        this.username = username;
        this.points = points;
        this.telegramAccount = telegramAccount;
        this.words = words;
        this.wordLists = wordLists;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
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

    public TelegramAccount getTelegramUser() {
        return telegramAccount;
    }

    public void setTelegramUser(TelegramAccount telegramAccount) {
        this.telegramAccount = telegramAccount;
    }

    public Set<Word> getWords() {
        return words;
    }

    public void setWords(Set<Word> words) {
        this.words = words;
    }

    public Set<WordList> getWordLists() {
        return wordLists;
    }

    public void setWordLists(Set<WordList> wordLists) {
        this.wordLists = wordLists;
    }

    @Override
    public String toString() {
        return "UserData{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", points=" + points +
                ", telegramUser=" + telegramAccount +
                ", words=" + words +
                ", wordLists=" + wordLists +
                '}';
    }
}
