package org.example.repository;

import org.example.model.UserData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserDataRepository extends JpaRepository<UserData, Long> {
    public List<UserData> findTop100ByOrderByExpDesc();

//    @Query(value = "SELECT ROW_NUMBER () OVER (ORDER BY points) FROM user_data WHERE user_id=:userId", nativeQuery = true)
//    public long findByIdWithRowNumber(@Param("userId") Long userId);
    public long countByExpGreaterThan(int exp);
}
