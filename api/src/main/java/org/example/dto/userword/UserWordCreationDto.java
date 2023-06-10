package org.example.dto.userword;

public class UserWordCreationDto {
    private Long wordId;
    private Boolean isFavorite;

    public UserWordCreationDto() {
    }

    public Long getWordId() {
        return wordId;
    }

    public void setWordId(Long wordId) {
        this.wordId = wordId;
    }

    public Boolean getIsFavorite() {
        return isFavorite;
    }

    public void setIsFavorite(boolean favorite) {
        isFavorite = favorite;
    }
}
