package org.example.dto.wordlist;

public class UserWordListDto {
    private Long userId;
    private Long wordListId;
    private Boolean isFavorite;
    private String difficulty;
    private String name;

    public UserWordListDto() {
    }

    public UserWordListDto(Long userId, Long wordListId, Boolean isFavorite, String difficulty, String name) {
        this.userId = userId;
        this.wordListId = wordListId;
        this.isFavorite = isFavorite;
        this.difficulty = difficulty;
        this.name = name;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getWordListId() {
        return wordListId;
    }

    public void setWordListId(Long wordListId) {
        this.wordListId = wordListId;
    }

    public Boolean getIsFavorite() {
        return isFavorite;
    }

    public void setIsFavorite(Boolean favorite) {
        isFavorite = favorite;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
