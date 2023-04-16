package org.example.repository;

import org.example.dto.userword.UserWordDto;
import org.example.model.UserdataWord;
import org.example.model.UserdataWordId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserWordRepository extends JpaRepository<UserdataWord, UserdataWordId> {
//    @Query(value = """
//            SELECT
//                uw.word_id,
//                uw.guess_streak,
//                uw.is_learned,
//                w.text,
//                c.category
//            FROM userdata_word AS uw
//            JOIN word AS w
//                ON uw.word_id = w.id
//            JOIN word_category AS c
//                ON c.id = w.category_id
//            WHERE uw.user_id = :user_id""", nativeQuery = true)
//    public List<UserWordDto> findAllByUserId(@Param("user_id") Long userId);
    public List<UserdataWord> findAllByUserData_Id(Long userId);
}
