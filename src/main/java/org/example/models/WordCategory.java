package org.example.models;

public class WordCategory {
    private int id;
    private String category;

    public WordCategory() {
    }

    public WordCategory(String category) {
        this.category = category;
    }

    public WordCategory(int id, String category) {
        this.id = id;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
