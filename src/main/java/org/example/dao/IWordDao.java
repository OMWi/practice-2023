package org.example.dao;

import org.example.entity.Word;

import java.util.Set;

public interface IWordDao {
    boolean add(Word word);

    Word get(int id);

    Set<Word> getByWordList(int wordListId);

    Set<Word> getByUser(int userId);

    Set<Word> getAll();

    boolean update(Word word);

    boolean remove(int id);
}
