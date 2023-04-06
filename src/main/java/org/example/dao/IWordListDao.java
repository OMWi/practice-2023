package org.example.dao;

import org.example.entity.WordList;

import java.util.Set;

public interface IWordListDao {
    boolean add(WordList wordList);

    WordList get(int id);

    Set<WordList> getByUser(int userId);

    Set<WordList> getAll();

    boolean update(int id, WordList wordList);

    boolean remove(int id);
}
