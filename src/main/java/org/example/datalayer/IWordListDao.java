package org.example.datalayer;

import org.example.models.WordList;

import java.util.List;

public interface IWordListDao {
    WordList getWordList(int id);

    List<WordList> getAllWordLists();

    boolean insertWordList(WordList wordList);

    boolean updateWordList(WordList wordList);

    boolean deleteWordList(int id);
}
