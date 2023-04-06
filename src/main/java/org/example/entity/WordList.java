package org.example.entity;

import java.util.Set;

public class WordList {
    private int id;
    private String name;
    private int wordCount;
    private int popularity;
    private Set<Word> words;

    public WordList(String name, int wordCount, int popularity, Set<Word> words) {
        this.name = name;
        this.wordCount = wordCount;
        this.popularity = popularity;
        this.words = words;
    }

    public WordList(int id, String name, int wordCount, int popularity, Set<Word> words) {
        this.id = id;
        this.name = name;
        this.wordCount = wordCount;
        this.popularity = popularity;
        this.words = words;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWordCount() {
        return wordCount;
    }

    public void setWordCount(int wordCount) {
        this.wordCount = wordCount;
    }

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    public Set<Word> getWords() {
        return words;
    }

    public void setWords(Set<Word> words) {
        this.words = words;
    }

    @Override
    public String toString() {
        return "WordList{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", wordCount=" + wordCount +
                ", popularity=" + popularity +
                ", words=" + words +
                '}';
    }
}
