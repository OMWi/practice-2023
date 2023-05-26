package org.example.dto.wordlist;

import java.util.List;

public class WordListCreationDto {
    private Long difficultyId;
    private String name;
    private List<Long> wordIdList;

    public WordListCreationDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Long> getWordIdList() {
        return wordIdList;
    }

    public void setWordIdList(List<Long> wordIdList) {
        this.wordIdList = wordIdList;
    }

    public Long getDifficultyId() {
        return difficultyId;
    }

    public void setDifficultyId(Long difficultyId) {
        this.difficultyId = difficultyId;
    }
}
