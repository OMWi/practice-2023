package org.example.entity;

public class UserHasWord {
    private UserData userData;
    private Word word;
    private boolean isLearned = false;
    private int guessStreak = 0;

    public UserHasWord(UserData userData, Word word) {
        this.userData = userData;
        this.word = word;
    }

    public UserHasWord(UserData userData, Word word, boolean isLearned, int guessStreak) {
        this.userData = userData;
        this.word = word;
        this.isLearned = isLearned;
        this.guessStreak = guessStreak;
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

    @Override
    public String toString() {
        return "UserHasWord{" +
                "userData=" + userData +
                ", word=" + word +
                ", isLearned=" + isLearned +
                ", guessStreak=" + guessStreak +
                '}';
    }
}
