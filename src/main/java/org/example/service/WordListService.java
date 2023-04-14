package org.example.service;

import org.example.model.WordList;
import org.example.repository.WordListRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class WordListService {
    private final WordListRepository wordListRepository;

    public WordListService(WordListRepository wordListRepository) {
        this.wordListRepository = wordListRepository;
    }

    public WordList create(WordList wordList) {
        return wordListRepository.save(wordList);
    }

    public List<WordList> list() {
        return wordListRepository.findAll();
    }

    public WordList get(Long wordListId) {
        return wordListRepository.findById(wordListId).orElseThrow();
    }

    public WordList update(WordList wordList) {
        if (!wordListRepository.existsById(wordList.getId())) throw new NoSuchElementException();
        return wordListRepository.save(wordList);
    }

    public void delete(Long wordListId) {
        if (!wordListRepository.existsById(wordListId)) throw new NoSuchElementException();
        wordListRepository.deleteById(wordListId);
    }

    public List<WordList> listByUser(Long userId) {
        return wordListRepository.findAllByUsers_UserDataId(userId);
    }
}
