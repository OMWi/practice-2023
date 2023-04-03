package org.example.model;

import java.util.List;

public class User {
    int id;
    String email;
    String hashed;
    String salt;
    String username;
    TelegramUser telegramUser;
    List<Word> words;
    List<WordList> listsOfWords;
    UserRole role = UserRole.USER;
    int points = 0;

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
}
