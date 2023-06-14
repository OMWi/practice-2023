package org.example.dto.wordlist;

import org.example.dto.userdata.UserDataDto;

public class WordListDto {
    private Long id;
    private String name;
    private String difficulty;
    private Long likes;
    private Long popularity;
    private UserDataDto owner;

    public WordListDto(Long id, String name, String difficulty, Long likes, Long popularity, UserDataDto owner) {
        this.id = id;
        this.name = name;
        this.difficulty = difficulty;
        this.likes = likes;
        this.popularity = popularity;
        this.owner = owner;
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

    public UserDataDto getOwner() {
        return owner;
    }

    public void setOwner(UserDataDto owner) {
        this.owner = owner;
    }
}
