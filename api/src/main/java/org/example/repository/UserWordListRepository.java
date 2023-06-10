package org.example.repository;

import org.example.model.UserdataWordlist;
import org.example.model.UserdataWordlistId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserWordListRepository extends JpaRepository<UserdataWordlist, UserdataWordlistId> {
    public List<UserdataWordlist> findAllByUserData_Id(Long userId);
    public long count();
    public long countByIsFavoriteTrue();
}
