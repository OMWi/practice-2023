package org.example.dto.word;

import org.example.dto.wordcategory.WordCategoryDto;

public class WordDto {
    private Long id;
    private String word;
    private WordCategoryDto categoryDto;
    private String difficulty;

    public WordDto() {
    }

    public WordDto(Long id, String word, WordCategoryDto categoryDto, String difficulty) {
        this.id = id;
        this.word = word;
        this.categoryDto = categoryDto;
        this.difficulty = difficulty;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public WordCategoryDto getCategoryDto() {
        return categoryDto;
    }

    public void setCategoryDto(WordCategoryDto categoryDto) {
        this.categoryDto = categoryDto;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }
}
