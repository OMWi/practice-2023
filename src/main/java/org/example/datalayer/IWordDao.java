package org.example.datalayer;

import org.example.models.Word;

import java.util.Set;

public interface IWordDao {
    Word getWord(int id);

    Set<Word> getAllWords();

    boolean insertWord(Word word);

    boolean updateWord(Word word);

    boolean deleteWord(int id);
}
