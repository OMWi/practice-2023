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

    @Column(nullable = false)
    private int popularity = 0;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "wordLists")
    private List<Word> words = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "userdata_wordlist",
            joinColumns = {@JoinColumn(name = "wordlist_id")},
            inverseJoinColumns = {@JoinColumn(name = "userdata_id")}
    )
    private List<UserData> userDataList;

    public WordList() {
    }

    public WordList(String name) {
        this.name = name;
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

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
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

    public List<UserData> getUserDataList() {
        return userDataList;
    }
}
