package org.example.model;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "user_data")
public class UserData {
    @Id
    @Column(name = "user_id")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @MapsId("user_id")
    @JoinColumn(name = "user_id")
    private UserCredentials userCredentials;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private int exp = 0;

    @Column
    private Date subscriptionExpirationDate = null;

//    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "userDataList")
//    private List<WordList> wordLists;

    @OneToMany(
            mappedBy = "userData",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<UserdataWordlist> wordLists;

    @OneToMany(
            mappedBy = "userData",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<UserdataWord> words;

    public UserData() {
    }

    public UserData(UserCredentials userCredentials, String username) {
        setUser(userCredentials);
        setUsername(username);
    }

    @Override
    public String toString() {
        return "UserData{" +
                "id=" + id +
                ", userCredentials=" + userCredentials +
                ", username='" + username + '\'' +
                ", exp=" + exp +
                ", subscriptionExpirationDate=" + subscriptionExpirationDate +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserCredentials getUser() {
        return userCredentials;
    }

    public void setUser(UserCredentials userCredentials) {
        this.userCredentials = userCredentials;
        userCredentials.setUserData(this);
        setId(userCredentials.getId());
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

//    public List<WordList> getWordLists() {
//        return wordLists;
//    }
//
//    public void addWordList(WordList wordList) {
//        this.wordLists.add(wordList);
//        wordList.getUserDataList().add(this);
//    }
//
//    public void removeWordList(WordList wordList) {
//        this.wordLists.remove(wordList);
//        wordList.getUserDataList().remove(this);
//    }

    public Date getSubscriptionExpirationDate() {
        return subscriptionExpirationDate;
    }

    public void setSubscriptionExpirationDate(Date subscriptionExpirationDate) {
        this.subscriptionExpirationDate = subscriptionExpirationDate;
    }
}
