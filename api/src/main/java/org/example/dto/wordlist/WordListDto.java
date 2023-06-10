package org.example.dto.wordlist;

public class WordListDto {
    private Long id;
    private String name;
    private String difficulty;
    private Long likes;
    private Long popularity;

    public WordListDto() {
    }

    public WordListDto(Long id, String name, String difficulty, Long likes, Long popularity) {
        this.id = id;
        this.name = name;
        this.difficulty = difficulty;
        this.likes = likes;
        this.popularity = popularity;
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
}
