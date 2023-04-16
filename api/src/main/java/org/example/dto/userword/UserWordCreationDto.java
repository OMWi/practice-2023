package org.example.dto.userword;

public class UserWordCreationDto {
    private Long userId;
    private Long wordId;

    public UserWordCreationDto() {
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
}
