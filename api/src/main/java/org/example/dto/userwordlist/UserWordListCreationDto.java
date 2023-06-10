package org.example.dto.userwordlist;

public class UserWordListCreationDto {
    private Long wordListId;
    private Boolean isFavorite;

    public UserWordListCreationDto() {
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
