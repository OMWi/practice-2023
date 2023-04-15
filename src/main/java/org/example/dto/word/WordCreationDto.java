package org.example.dto;

import java.util.List;

public class WordCreationDto {
    private String text;
    private Long categoryId;
    private List<MeaningDto> meaningDtoList;

    public WordCreationDto() {
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

    public List<MeaningDto> getMeaningDtoList() {
        return meaningDtoList;
    }

    public void setMeaningDtoList(List<MeaningDto> meaningDtoList) {
        this.meaningDtoList = meaningDtoList;
    }
}
