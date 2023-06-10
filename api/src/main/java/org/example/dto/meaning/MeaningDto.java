package org.example.dto.meaning;

public class MeaningDto {
    private Long id;
    private String difficulty;
    private String meaning;
    private Long wordId;

    public MeaningDto() {
    }

    public MeaningDto(Long id, String difficulty, String meaning, Long wordId) {
        this.id = id;
        this.difficulty = difficulty;
        this.meaning = meaning;
        this.wordId = wordId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
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
