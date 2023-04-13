package org.example.repository;

import org.example.model.WordList;
import org.springframework.data.repository.CrudRepository;

public interface WordListRepository extends CrudRepository<WordList, Long> {
}
