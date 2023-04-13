package org.example.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "user_data")
public class UserData {
    @Id
    @Column(name = "user_id")
    private Long id;

    @OneToOne
    @MapsId("user_id")
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private int points = 0;

    @OneToOne(mappedBy = "userData", cascade = CascadeType.ALL, orphanRemoval = true)
    private TelegramAccount telegramAccount;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "listOfUserData")
    private List<WordList> wordLists;

    @OneToMany(
            mappedBy = "userData",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<UserdataWord> words;

    public UserData() {
    }

    public UserData(User user, String username) {
        this.user = user;
        this.username = username;
    }

    @Override
    public String toString() {
        return "UserData{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", points=" + points +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public TelegramAccount getTelegramAccount() {
        return telegramAccount;
    }

    public void setTelegramAccount(TelegramAccount telegramAccount) {
        this.telegramAccount = telegramAccount;
    }

    public List<WordList> getWordLists() {
        return wordLists;
    }

    public void addWordList(WordList wordList) {
        this.wordLists.add(wordList);
        wordList.getListOfUserData().add(this);
    }

    public void removeWordList(WordList wordList) {
        this.wordLists.remove(wordList);
        wordList.getListOfUserData().remove(this);
    }

}