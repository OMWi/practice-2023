package org.example.datalayer;

import org.example.models.WordType;

import java.util.List;

public interface IWordTypeDao {
    WordType getWordType(int id);

    List<WordType> getAllWordTypes();

    boolean insertWordType(WordType wordType);

    boolean updateWordType(WordType wordType);

    boolean deleteWordType(int id);
}
