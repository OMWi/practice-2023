package org.example.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "wordlist")
public class WordList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "difficulty_id")
    private Difficulty difficulty;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private UserData owner;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "wordLists")
    private List<Word> words = new ArrayList<>();

    @OneToMany(
            mappedBy = "wordList",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<UserdataWordlist> users;

    public WordList() {
    }

    public WordList(String name, Difficulty difficulty, UserData owner) {
        this.name = name;
        this.difficulty = difficulty;
        this.owner = owner;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Word> getWords() {
        return words;
    }

    public void addWord(Word word) {
        this.words.add(word);
        word.getWordLists().add(this);
    }

    public void removeWord(Word word) {
        this.words.remove(word);
        word.getWordLists().remove(this);
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public List<UserdataWordlist> getUsers() {return users;}

    public UserData getOwner() {
        return owner;
    }

    public void setOwner(UserData owner) {
        this.owner = owner;
    }
}
