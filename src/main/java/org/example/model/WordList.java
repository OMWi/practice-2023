package org.example.model;

import java.util.List;

public class WordList {
    private int id;
    private String name;
    private int wordCount;
    private int popularity;
    private List<Word> words;

    public WordList() {
    }

    public WordList(String name, int wordCount, int popularity, List<Word> words) {
        this.name = name;
        this.wordCount = wordCount;
        this.popularity = popularity;
        this.words = words;
    }

    public WordList(int id, String name, int wordCount, int popularity, List<Word> words) {
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

    public List<Word> getWords() {
        return words;
    }

    public void setWords(List<Word> words) {
        this.words = words;
    }
}
