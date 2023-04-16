package org.example.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "word_category")
public class WordCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String category;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<Word> words;

    public WordCategory() {
    }

    public WordCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "WordCategory{" +
                "id=" + id +
                ", category='" + category + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<Word> getWords() {
        return words;
    }
}
