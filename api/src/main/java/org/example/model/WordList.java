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

//    @Column(nullable = false)
//    private long popularity = 0;
//
//    @Column(nullable = false)
//    private long likes = 0;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "wordLists")
    private List<Word> words = new ArrayList<>();

//    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JoinTable(
//            name = "userdata_wordlist",
//            joinColumns = {@JoinColumn(name = "wordlist_id")},
//            inverseJoinColumns = {@JoinColumn(name = "userdata_id")}
//    )
//    private List<UserData> userDataList;

    @OneToMany(
            mappedBy = "wordList",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<UserdataWordlist> users;

    public WordList() {
    }

    public WordList(String name, Difficulty difficulty) {
        this.name = name;
        this.difficulty = difficulty;
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

//    public long getPopularity() {
//        return popularity;
//    }
//
//    public void setPopularity(long popularity) {
//        this.popularity = popularity;
//    }

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

//    public List<UserData> getUserDataList() {
//        return userDataList;
//    }

//    public long getLikes() {
//        return likes;
//    }
//
//    public void setLikes(long likes) {
//        this.likes = likes;
//    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public List<UserdataWordlist> getUsers() {return users;}

}
