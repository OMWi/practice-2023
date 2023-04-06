package org.example.datalayer;

import org.example.models.Meaning;
import org.example.models.Word;

import java.util.Set;

public interface IMeaningDao {
    Meaning getMeaning(int id);

    Set<Meaning> getAllMeanings();

    Set<Meaning> getMeanings(int wordId);

    boolean insertMeaning(Word word, Meaning meaning);

    boolean updateMeaning(Meaning meaning);

    boolean deleteMeaning(int id);
}
