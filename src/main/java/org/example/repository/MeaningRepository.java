package org.example.repository;

import org.example.model.Meaning;
import org.springframework.data.repository.CrudRepository;

public interface MeaningRepository extends CrudRepository<Meaning, Long> {
}
