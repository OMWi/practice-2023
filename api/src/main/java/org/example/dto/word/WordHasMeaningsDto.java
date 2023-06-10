package org.example.dto.word;

import org.example.dto.meaning.MeaningDto;
import org.example.dto.wordcategory.WordCategoryDto;

import java.util.List;

public class WordHasMeaningsDto {
  private Long id;
  private String word;
  private WordCategoryDto categoryDto;
  private List<MeaningDto> meaningDtoList;
  private String difficulty;

  public WordHasMeaningsDto() {
  }

  public WordHasMeaningsDto(Long id, String word, WordCategoryDto categoryDto, List<MeaningDto> meaningDtoList, String difficulty) {
    this.id = id;
    this.word = word;
    this.categoryDto = categoryDto;
    this.meaningDtoList = meaningDtoList;
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

  public List<MeaningDto> getMeaningDtoList() {
    return meaningDtoList;
  }

  public void setMeaningDtoList(List<MeaningDto> meaningDtoList) {
    this.meaningDtoList = meaningDtoList;
  }

  public String getDifficulty() {
    return difficulty;
  }

  public void setDifficulty(String difficulty) {
    this.difficulty = difficulty;
  }
}