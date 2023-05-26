package org.example.repository;

import org.example.model.Difficulty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DifficultyRepository extends JpaRepository<Difficulty, Long> {
}
