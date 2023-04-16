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
    private String text;

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

    public Word(String text, WordCategory category) {
        this.text = text;
        this.category = category;
    }

    @Override
    public String toString() {
        return "Word{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", category=" + category +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
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
}
