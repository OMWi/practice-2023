package org.example.dao;

import org.example.entity.Meaning;

import java.util.Set;

public interface IMeaningDao {
    boolean add(int wordId, Meaning meaning);

    Meaning get(int id);

    Set<Meaning> getAll();

    Set<Meaning> getMeaningsOfWord(int wordId);

    boolean update(int id, Meaning meaning);

    boolean remove(int id);
}
