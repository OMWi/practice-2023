package org.example.repository;

import org.example.model.Word;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface WordRepository extends JpaRepository<Word, Long> {
    public Page<Word> findAllByUsers_UserDataId(Long userId, Pageable paging);
}
