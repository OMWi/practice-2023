package org.example.dto.wordlist;

import java.util.List;

public class WordListCreationDto {
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
}
