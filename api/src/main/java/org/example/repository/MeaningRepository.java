package org.example.repository;

import org.example.model.Meaning;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MeaningRepository extends JpaRepository<Meaning, Long> {
    List<Meaning> findAllByWord_Id(Long wordId);
}
