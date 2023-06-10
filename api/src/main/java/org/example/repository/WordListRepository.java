package org.example.repository;

import org.example.model.WordList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WordListRepository extends JpaRepository<WordList, Long> {
//    List<WordList> findAllByUserDataList_Id(Long wordId);
}
