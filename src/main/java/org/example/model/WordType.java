package org.example.model;

public class WordType {
    private int id;
    private String type;

    public WordType() {
    }

    public WordType(String type) {
        this.type = type;
    }

    public WordType(int id, String type) {
        this.id = id;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
