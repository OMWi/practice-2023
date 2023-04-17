package org.example.dto.word;

import org.example.dto.wordcategory.WordCategoryDto;

public class WordDto {
    private Long id;
    private String text;
    private WordCategoryDto categoryDto;

    public WordDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public WordCategoryDto getCategoryDto() {
        return categoryDto;
    }

    public void setCategoryDto(WordCategoryDto categoryDto) {
        this.categoryDto = categoryDto;
    }

}
