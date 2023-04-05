package org.example.models;

import java.util.Set;

public class UserData {
    private int id;
    private String username;
    private int points;
    private TelegramUser telegramUser;
    private Set<Word> words;
    private Set<WordList> wordLists;

    public UserData() {
    }

    public UserData(int id, String username, int points, TelegramUser telegramUser, Set<Word> words, Set<WordList> wordLists) {
        this.id = id;
        this.username = username;
        this.points = points;
        this.telegramUser = telegramUser;
        this.words = words;
        this.wordLists = wordLists;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public TelegramUser getTelegramUser() {
        return telegramUser;
    }

    public void setTelegramUser(TelegramUser telegramUser) {
        this.telegramUser = telegramUser;
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
}
