package org.example.dto;

public class WordDto {
    private String text;
    private Long categoryId;

    public WordDto() {
    }

    public WordDto(String text, Long categoryId) {
        this.text = text;
        this.categoryId = categoryId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}
