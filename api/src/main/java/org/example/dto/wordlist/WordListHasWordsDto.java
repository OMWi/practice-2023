package org.example.dto.wordlist;

import org.example.dto.word.WordDto;

import java.util.List;

public class WordListHasWordsDto {
    private Long id;
    private String name;
    private int popularity;
    private List<WordDto> wordDtoList;

    public WordListHasWordsDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    public List<WordDto> getWordDtoList() {
        return wordDtoList;
    }

    public void setWordDtoList(List<WordDto> wordDtoList) {
        this.wordDtoList = wordDtoList;
    }
}
