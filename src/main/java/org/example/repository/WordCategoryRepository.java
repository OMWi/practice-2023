package org.example.repository;

import org.example.model.WordCategory;
import org.springframework.data.repository.CrudRepository;

public interface WordCategoryRepository extends CrudRepository<WordCategory, Long> {
}
