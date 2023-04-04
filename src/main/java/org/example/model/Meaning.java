package org.example.model;

public class Meaning {
    private int id;
    private int level;
    private String text;

    public Meaning() {
    }

    public Meaning(int level, String text) {
        this.level = level;
        this.text = text;
    }

    public Meaning(int id, int level, String text) {
        this.id = id;
        this.level = level;
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
