package org.example.model;

import jakarta.persistence.*;

@Entity
@Table(name = "userdata_wordlist")
public class UserdataWordlist {
    @EmbeddedId
    private UserdataWordlistId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userdata_id")
    @JoinColumn(name = "userdata_id")
    private UserData userData;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("wordlist_id")
    @JoinColumn(name = "wordlist_id")
    private WordList wordList;

    @Column(nullable = false, name = "is_favorite")
    private Boolean isFavorite = false;

    public UserdataWordlist() {
    }

    public UserdataWordlist(UserData userData, WordList wordList) {
        this.userData = userData;
        this.wordList = wordList;
        this.id = new UserdataWordlistId(userData.getId(), wordList.getId());
    }

    public UserdataWordlistId getId() {
        return id;
    }

    public void setId(UserdataWordlistId id) {
        this.id = id;
    }

    public UserData getUserData() {
        return userData;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }

    public WordList getWordList() {
        return wordList;
    }

    public void setWordList(WordList wordList) {
        this.wordList = wordList;
    }

    public Boolean getIsFavorite() {
        return isFavorite;
    }

    public void setIsFavorite(Boolean favorite) {
        isFavorite = favorite;
    }
}
