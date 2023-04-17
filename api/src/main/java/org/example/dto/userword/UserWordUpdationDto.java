package org.example.dto.userword;

public class UserWordUpdationDto {
    private Long wordId;
    private boolean isLearned;
    private int guessStreak;

    public UserWordUpdationDto() {
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

    public Long getWordId() {
        return wordId;
    }

    public void setWordId(Long wordId) {
        this.wordId = wordId;
    }
}
