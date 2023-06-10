package org.example.dto.wordlist;

import org.example.dto.word.WordDto;

import java.util.List;

public class WordListHasWordsDto {
    private Long id;
    private String name;
    private String difficulty;
    private Long likes;
    private Long popularity;
    private List<WordDto> wordDtoList;

    public WordListHasWordsDto() {
    }

    public WordListHasWordsDto(Long id, String name, String difficulty, Long likes, Long popularity, List<WordDto> wordDtoList) {
        this.id = id;
        this.name = name;
        this.difficulty = difficulty;
        this.likes = likes;
        this.popularity = popularity;
        this.wordDtoList = wordDtoList;
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

    public Long getPopularity() {
        return popularity;
    }

    public void setPopularity(Long popularity) {
        this.popularity = popularity;
    }

    public List<WordDto> getWordDtoList() {
        return wordDtoList;
    }

    public void setWordDtoList(List<WordDto> wordDtoList) {
        this.wordDtoList = wordDtoList;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public Long getLikes() {
        return likes;
    }

    public void setLikes(Long likes) {
        this.likes = likes;
    }
}
