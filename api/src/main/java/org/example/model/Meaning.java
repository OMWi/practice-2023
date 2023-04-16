package org.example.model;

import jakarta.persistence.*;
import org.hibernate.Length;

@Entity
@Table(name = "meaning")
public class Meaning {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int level;

    @Column(nullable = false, length = Length.LONG)
    private String text;

    @ManyToOne
    private Word word;

    public Meaning() {
    }

    public Meaning(int level, String text) {
        this.level = level;
        this.text = text;
    }

    @Override
    public String toString() {
        return "Meaning{" +
                "id=" + id +
                ", level=" + level +
                ", text='" + text + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Word getWord() {
        return word;
    }

    public void setWord(Word word) {
        this.word = word;
    }
}
