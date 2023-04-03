package org.example.model;

import java.util.ArrayList;

public class Word {
    int id;
    String word;
    WordType type;
    ArrayList<Meaning> meanings;

    public Word(String word, WordType type, ArrayList<Meaning> meanings) {
        this.word = word;
        this.type = type;
        this.meanings = meanings;
    }
}
