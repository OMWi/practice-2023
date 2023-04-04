package org.example.models;

import java.util.Set;

public class Word {
    private int id;
    private String text;
    private WordType type;
    private Set<Meaning> meanings;

    public Word() {
    }

    public Word(String text, WordType type, Set<Meaning> meanings) {
        this.text = text;
        this.type = type;
        this.meanings = meanings;
    }

    public Word(int id, String text, WordType type, Set<Meaning> meanings) {
        this.id = id;
        this.text = text;
        this.type = type;
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

    public WordType getType() {
        return type;
    }

    public void setType(WordType type) {
        this.type = type;
    }

    public Set<Meaning> getMeanings() {
        return meanings;
    }

    public void setMeanings(Set<Meaning> meanings) {
        this.meanings = meanings;
    }
}
