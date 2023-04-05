package org.example.models;

import java.util.Set;

public class Word {
    private int id;
    private String text;
    private WordCategory category;
    private Set<Meaning> meanings;

    public Word() {
    }

    public Word(String text, WordCategory category, Set<Meaning> meanings) {
        this.text = text;
        this.category = category;
        this.meanings = meanings;
    }

    public Word(int id, String text, WordCategory category, Set<Meaning> meanings) {
        this.id = id;
        this.text = text;
        this.category = category;
        this.meanings = meanings;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public Set<Meaning> getMeanings() {
        return meanings;
    }

    public void setMeanings(Set<Meaning> meanings) {
        this.meanings = meanings;
    }
}
