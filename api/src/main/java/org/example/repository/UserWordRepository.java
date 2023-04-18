package org.example.repository;

import org.example.model.UserdataWord;
import org.example.model.UserdataWordId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserWordRepository extends JpaRepository<UserdataWord, UserdataWordId> {
    public List<UserdataWord> findAllByUserData_Id(Long userId);

    @Query(nativeQuery = true, value = "SELECT * FROM userdata_word WHERE userdata_id=:userId AND is_learned=false ORDER BY random() LIMIT 1")
    public UserdataWord findRandomUnlearned(@Param("userId") Long userId);
}
