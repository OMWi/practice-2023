package org.example.model;

import jakarta.persistence.*;

import java.sql.Date;

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
    private Boolean isLearned = false;

    @Column(nullable = false, name = "guess_streak")
    private Integer guessStreak = 0;

    @Column(nullable = false, name = "interval_change_date")
    private Date intervalChangeDate = new Date(System.currentTimeMillis());

    @Column(nullable = false, name = "repeat_interval")
    private Integer interval = 0;

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
                ", userData=" + userData.getUser().getEmail() +
                ", word=" + word.getWord() +
                ", isLearned=" + isLearned +
                ", guessStreak=" + guessStreak +
                ", intervalChangeDate=" + intervalChangeDate +
                ", interval=" + interval +
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

    public Boolean getIsLearned() {
        return isLearned;
    }

    public void setIsLearned(Boolean learned) {
        isLearned = learned;
    }

    public Integer getGuessStreak() {
        return guessStreak;
    }

    public void setGuessStreak(Integer guessStreak) {
        this.guessStreak = guessStreak;
    }

    public Date getIntervalChangeDate() {
        return intervalChangeDate;
    }

    public void setIntervalChangeDate(Date intervalChangeDate) {
        this.intervalChangeDate = intervalChangeDate;
    }

    public Integer getInterval() {
        return interval;
    }

    public void setInterval(Integer interval) {
        this.interval = interval;
    }
}
