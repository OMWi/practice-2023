package org.example.datalayer;

import org.example.models.Word;

import java.util.List;

public interface IWordDao {
    Word getWord(int id);

    List<Word> getAllWords();

    boolean insertWord(Word word);

    boolean updateWord(Word word);

    boolean deleteWord(int id);
}
