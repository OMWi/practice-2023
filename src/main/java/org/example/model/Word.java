package org.example.model;

import java.util.List;

public class Word {
    private int id;
    private String text;
    private WordType type;
    private List<Meaning> meanings;

    public Word() {
    }

    public Word(String text, WordType type, List<Meaning> meanings) {
        this.text = text;
        this.type = type;
        this.meanings = meanings;
    }

    public Word(int id, String text, WordType type, List<Meaning> meanings) {
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

    public List<Meaning> getMeanings() {
        return meanings;
    }

    public void setMeanings(List<Meaning> meanings) {
        this.meanings = meanings;
    }
}
