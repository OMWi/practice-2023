package org.example.dto.userword;

public class UserWordUpdationDto {
    private Long wordId;
    private Boolean isFavorite;

    public UserWordUpdationDto() {
    }

    public Boolean getIsFavorite() {
        return isFavorite;
    }

    public void setIsFavorite(Boolean favorite) {
        isFavorite = favorite;
    }

    public Long getWordId() {
        return wordId;
    }

    public void setWordId(Long wordId) {
        this.wordId = wordId;
    }
}
