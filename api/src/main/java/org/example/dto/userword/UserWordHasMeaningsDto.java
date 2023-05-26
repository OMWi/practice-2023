package org.example.dto.userword;

import org.example.dto.meaning.MeaningDto;

import java.sql.Date;
import java.util.List;

public class UserWordHasMeaningsDto {
    private Long userId;
    private Long wordId;
    private boolean isLearned;
    private int guessStreak;
    private String word;
    private String difficulty;
    private Date intervalChangeDate;
    private int interval;
    private String category;
    private List<MeaningDto> meaningDtoList;

    public UserWordHasMeaningsDto() {
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

    public boolean getIsLearned() {
        return isLearned;
    }

    public void setIsLearned(boolean learned) {
        isLearned = learned;
    }

    public int getGuessStreak() {
        return guessStreak;
    }

    public void setGuessStreak(int guessStreak) {
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

    public List<MeaningDto> getMeaningDtoList() {
        return meaningDtoList;
    }

    public void setMeaningDtoList(List<MeaningDto> meaningDtoList) {
        this.meaningDtoList = meaningDtoList;
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

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }
}
