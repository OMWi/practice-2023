package org.example.service;

import org.example.model.Word;
import org.example.repository.WordCategoryRepository;
import org.example.repository.WordRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class WordService {
    private final WordRepository wordRepository;
    private final WordCategoryRepository wordCategoryRepository;

    public WordService(WordRepository wordRepository, WordCategoryRepository wordCategoryRepository) {
        this.wordRepository = wordRepository;
        this.wordCategoryRepository = wordCategoryRepository;
    }

    public Word create(Word word) {
//        var category = wordCategoryRepository.findById(wordDto.getCategoryId()).orElseThrow();
//        var word = new Word(wordDto.getText(), category);

        return wordRepository.save(word);
    }

    public List<Word> list() {
        return wordRepository.findAll();
    }

    public Word get(Long wordId) {
        return wordRepository.findById(wordId).orElseThrow();
    }

    public Word update(Word word) {
        if (!wordRepository.existsById(word.getId())) throw new NoSuchElementException();
        return wordRepository.save(word);
    }

    public void delete(Long wordId) {
        if (!wordRepository.existsById(wordId)) throw new NoSuchElementException();
        wordRepository.deleteById(wordId);
    }

    public List<Word> listByUser(Long userId) {
        return wordRepository.findAllByUsers_UserDataId(userId);
    }
}
