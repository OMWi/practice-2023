package org.example.model;

import jakarta.persistence.*;
import org.hibernate.Length;

@Entity
@Table(name = "meaning")
public class Meaning {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "difficulty_id")
    private Difficulty difficulty;

    @Column(nullable = false, length = Length.LONG)
    private String meaning;

    @ManyToOne
    private Word word;

    public Meaning() {
    }

    public Meaning(Difficulty difficulty, String meaning) {
        this.difficulty = difficulty;
        this.meaning = meaning;
    }

    @Override
    public String toString() {
        return "Meaning{" +
                "id=" + id +
                ", difficulty=" + difficulty.getDifficulty() +
                ", meaning='" + meaning + '\'' +
                ", word=" + word.getWord() +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public Word getWord() {
        return word;
    }

    public void setWord(Word word) {
        this.word = word;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }
}
