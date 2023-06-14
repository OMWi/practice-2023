package org.example.repository;

import org.example.dto.wordlist.WordListDto;
import org.example.dto.wordlist.WordListProjection;
import org.example.model.Word;
import org.example.model.WordList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WordListRepository extends JpaRepository<WordList, Long> {
    @Query(
            value = """
                        SELECT
                          w.id,
                          w.name,
                          w.owner_id AS ownerId,
                          d.difficulty,
                          COALESCE(SUM(CAST(uw.is_favorite AS int)), 0) AS likes,
                          COUNT(uw.wordlist_id) AS popularity
                        FROM
                          wordlist w
                        LEFT JOIN difficulty d ON d.id = w.difficulty_id
                        LEFT JOIN userdata_wordlist uw ON uw.wordlist_id = w.id
                        WHERE
                          (:searchText IS NULL OR w.name LIKE %:searchText%)
                          AND (:difficultyId IS NULL OR w.difficulty_id = :difficultyId)
                        GROUP BY
                          w.id, d.id
                        ORDER BY
                          CASE WHEN :sortBy IS NULL THEN NULL ELSE
                            CASE :sortBy
                              WHEN 'popularity' THEN COUNT(uw.wordlist_id)
                              WHEN 'likes' THEN COALESCE(SUM(CAST(uw.is_favorite AS int)), 0)
                              ELSE NULL
                            END
                          END DESC;""",
            countQuery = """
                    SELECT COUNT(*)                  
                    FROM wordlist w
                    WHERE
                      (:searchText IS NULL OR w.name LIKE %:searchText%)
                      AND (:difficultyId IS NULL OR w.difficulty_id = :difficultyId)""",
            nativeQuery = true
    )
    public Page<WordListProjection> findAllByCondition(Pageable pageable, String searchText, Integer difficultyId, String sortBy);
}
