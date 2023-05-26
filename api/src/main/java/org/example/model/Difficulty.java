package org.example.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Difficulty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String difficulty;

    @OneToMany(mappedBy = "difficulty", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<Word> words;

    @OneToMany(mappedBy = "difficulty", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<Word> meanings;

    @OneToMany(mappedBy = "difficulty", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<Word> wordLists;

    public Difficulty() {
    }

    public Difficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    @Override
    public String toString() {
        return "Difficulty{" +
                "id=" + id +
                ", difficulty='" + difficulty + '\'' +
                '}';
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

    public List<Word> getWords() {
        return words;
    }

    public void setWords(List<Word> words) {
        this.words = words;
    }

    public List<Word> getWordLists() {
        return wordLists;
    }

    public void setWordLists(List<Word> wordLists) {
        this.wordLists = wordLists;
    }

    public List<Word> getMeanings() {
        return meanings;
    }

    public void setMeanings(List<Word> meanings) {
        this.meanings = meanings;
    }
}
