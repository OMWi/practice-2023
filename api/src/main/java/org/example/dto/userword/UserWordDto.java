package org.example.dto.userword;

import java.sql.Date;

public class UserWordDto {
    private Long userId;
    private Long wordId;
    private Boolean isLearned;
    private Integer guessStreak;
    private String word;
    private String category;
    private String difficulty;
    private Date intervalChangeDate;
    private Integer interval;

    public UserWordDto() {
    }

    public UserWordDto(Long userId, Long wordId, boolean isLearned, int guessStreak, String word, String category, String difficulty, Date intervalChangeDate, int interval) {
        this.userId = userId;
        this.wordId = wordId;
        this.isLearned = isLearned;
        this.guessStreak = guessStreak;
        this.word = word;
        this.category = category;
        this.difficulty = difficulty;
        this.intervalChangeDate = intervalChangeDate;
        this.interval = interval;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getWordId() {
        return wordId;
    }

    public void setWordId(Long wordId) {
        this.wordId = wordId;
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

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
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
