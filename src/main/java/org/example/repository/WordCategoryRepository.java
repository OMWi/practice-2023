package org.example.repository;

import org.example.model.WordCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WordCategoryRepository extends JpaRepository<WordCategory, Long> {
}
