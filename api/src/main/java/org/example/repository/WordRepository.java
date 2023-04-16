package org.example.repository;

import org.example.model.Word;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WordRepository extends JpaRepository<Word, Long> {
    public List<Word> findAllByUsers_UserDataId(Long userId);
}
