package org.example.model;

import java.util.List;

public class Word {
    int id;
    String text;
    WordType type;
    List<Meaning> meanings;

    public Word(String text, WordType type, List<Meaning> meanings) {
        this.text = text;
        this.type = type;
        this.meanings = meanings;
    }
}
