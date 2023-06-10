package org.example.dto.wordlist;

public class UserWordListDto {
    private Long userId;
    private Long wordListId;
    private Boolean isFavorite;

    public UserWordListDto() {
    }

    public UserWordListDto(Long userId, Long wordListId, Boolean isFavorite) {
        this.userId = userId;
        this.wordListId = wordListId;
        this.isFavorite = isFavorite;
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
}
