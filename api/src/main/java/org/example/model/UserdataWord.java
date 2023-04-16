package org.example.model;

import jakarta.persistence.*;

@Entity
@Table(name = "userdata_word")
public class UserdataWord {
    @EmbeddedId
    private UserdataWordId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userdata_id")
    @JoinColumn(name = "userdata_id")
    private UserData userData;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("word_id")
    @JoinColumn(name = "word_id")
    private Word word;

    @Column(nullable = false, name = "is_learned")
    private boolean isLearned = false;

    @Column(nullable = false, name = "guess_streak")
    private int guessStreak = 0;

    public UserdataWord() {
    }

    public UserdataWord(UserData userData, Word word) {
        this.userData = userData;
        this.word = word;
        this.id = new UserdataWordId(userData.getId(), word.getId());
    }

    @Override
    public String toString() {
        return "UserdataWord{" +
                "id=" + id +
                ", userData=" + userData +
                ", word=" + word +
                ", isLearned=" + isLearned +
                ", guessStreak=" + guessStreak +
                '}';
    }

    public UserdataWordId getId() {
        return id;
    }

    public void setId(UserdataWordId id) {
        this.id = id;
    }

    public UserData getUserData() {
        return userData;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }

    public Word getWord() {
        return word;
    }

    public void setWord(Word word) {
        this.word = word;
    }

    public boolean isLearned() {
        return isLearned;
    }

    public void setLearned(boolean learned) {
        isLearned = learned;
    }

    public int getGuessStreak() {
        return guessStreak;
    }

    public void setGuessStreak(int guessStreak) {
        this.guessStreak = guessStreak;
    }
}
