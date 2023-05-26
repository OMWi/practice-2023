package org.example.dto.meaning;

public class MeaningCreationDto {
    private Long id;
    private Long difficultyId;
    private String meaning;
    private Long wordId;

    public MeaningCreationDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDifficultyId() {
        return difficultyId;
    }

    public void setDifficultyId(Long difficultyId) {
        this.difficultyId = difficultyId;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public Long getWordId() {
        return wordId;
    }

    public void setWordId(Long wordId) {
        this.wordId = wordId;
    }
}
