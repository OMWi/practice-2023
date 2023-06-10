package org.example.dto.difficulty;

public class DifficultyDto {
    private Long id;
    private String difficulty;

    public DifficultyDto() {
    }

    public DifficultyDto(Long id, String difficulty) {
        this.id = id;
        this.difficulty = difficulty;
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
}
