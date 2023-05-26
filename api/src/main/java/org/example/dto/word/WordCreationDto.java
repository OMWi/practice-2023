package org.example.dto.word;

import org.example.dto.meaning.MeaningCreationDto;

import java.util.List;

public class WordCreationDto {
    private String word;
    private Long categoryId;
    private Long difficultyId;
    private List<MeaningCreationDto> meaningDtoList;

    public WordCreationDto() {
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public List<MeaningCreationDto> getMeaningDtoList() {
        return meaningDtoList;
    }

    public void setMeaningDtoList(List<MeaningCreationDto> meaningDtoList) {
        this.meaningDtoList = meaningDtoList;
    }

    public Long getDifficultyId() {
        return difficultyId;
    }

    public void setDifficultyId(Long difficultyId) {
        this.difficultyId = difficultyId;
    }
}
