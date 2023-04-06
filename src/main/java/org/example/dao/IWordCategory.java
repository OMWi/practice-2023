package org.example.dao;

import org.example.entity.WordCategory;

import java.util.Set;

public interface IWordCategory {
    boolean add(WordCategory wordCategory);

    WordCategory get(int id);

    Set<WordCategory> getAll();

    boolean update(int id, WordCategory wordCategory);

    boolean remove(int id);
}
