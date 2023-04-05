package org.example.datalayer;

import org.example.models.WordCategory;

import java.util.List;

public interface IWordCategory {
    WordCategory getWordCategory(int id);

    List<WordCategory> getAllWordCategories();

    boolean insertWordCategory(WordCategory wordCategory);

    boolean updateWordCategory(WordCategory wordCategory);

    boolean deleteWordCategory(int id);
}