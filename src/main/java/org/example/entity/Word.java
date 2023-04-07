package org.example.entity;

import java.util.Set;

public class Word {
    private int id;
    private String text;
    private WordCategory category;
    private Set<Meaning> meanings;

    public Word(String text, WordCategory category) {
        this.text = text;
        this.category = category;
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

    @Override
    public String toString() {
        return "Word{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", category=" + category +
                ", meanings=" + meanings +
                '}';
    }
}
