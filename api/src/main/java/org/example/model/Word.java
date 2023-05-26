package org.example.model;

import jakarta.persistence.*;
import org.hibernate.Length;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "word")
public class Word {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = Length.LONG)
    private String word;

    @ManyToOne
    @JoinColumn(name = "difficulty_id")
    private Difficulty difficulty;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private WordCategory category;

    @OneToMany(mappedBy = "word", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Meaning> meanings = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "wordlist_word",
            joinColumns = {@JoinColumn(name = "word_id")},
            inverseJoinColumns = {@JoinColumn(name = "wordlist_id")}
    )
    private List<WordList> wordLists;

    @OneToMany(
            mappedBy = "word",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<UserdataWord> users;

    public Word() {
    }

    public Word(String word, WordCategory category, Difficulty difficulty) {
        this.word = word;
        this.category = category;
        this.difficulty = difficulty;
    }

    @Override
    public String toString() {
        return "Word{" +
                "id=" + id +
                ", text='" + word + '\'' +
                ", category=" + category +
                '}';
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

    public WordCategory getCategory() {
        return category;
    }

    public void setCategory(WordCategory category) {
        this.category = category;
    }

    public List<Meaning> getMeanings() {
        return meanings;
    }

    public void addMeaning(Meaning meaning) {
        this.meanings.add(meaning);
        meaning.setWord(this);
    }

    public void removeMeaning(Meaning meaning) {
        meaning.setWord(null);
        this.meanings.remove(meaning);
    }

    public List<UserdataWord> getUsers() {
        return users;
    }

    public List<WordList> getWordLists() {
        return wordLists;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }
}
