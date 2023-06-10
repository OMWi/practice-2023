package org.example.dto.userwordlist;

public class UserWordListDto {
    private Long wordListId;
    private Boolean isFavorite;

    public UserWordListDto() {
    }

    public Long getWordListId() {
        return wordListId;
    }

    public void setWordListId(Long wordListId) {
        this.wordListId = wordListId;
    }

    public Boolean getFavorite() {
        return isFavorite;
    }

    public void setFavorite(Boolean favorite) {
        isFavorite = favorite;
    }
}
