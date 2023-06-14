package org.example.repository;

import org.example.model.Word;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface WordRepository extends JpaRepository<Word, Long> {
    public Page<Word> findAllByUsers_UserDataId(Long userId, Pageable paging);

    @Query(
            value = "SELECT * FROM word w WHERE (:searchText IS NULL OR w.word LIKE %:searchText%) AND (:categoryId IS NULL OR w.category_id = :categoryId) AND (:difficultyId IS NULL OR w.difficulty_id = :difficultyId)",
            countQuery = "SELECT COUNT(*) FROM word w WHERE (:searchText IS NULL OR w.word LIKE %:searchText%) AND (:categoryId IS NULL OR w.category_id = :categoryId) AND (:difficultyId IS NULL OR w.difficulty_id = :difficultyId)",
            nativeQuery = true
    )
    public Page<Word> findAllByCondition(Pageable pageable, String searchText, Integer categoryId, Integer difficultyId);
}
