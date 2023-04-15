package org.example.dto.wordlist;

public class UserWordListDto {
    private Long userId;
    private Long wordListId;

    public UserWordListDto() {
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
}
