package org.example.service;

import org.example.model.WordCategory;
import org.example.repository.WordCategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class WordCategoryService {
    private final WordCategoryRepository wordCategoryRepository;

    public WordCategoryService(WordCategoryRepository wordCategoryRepository) {
        this.wordCategoryRepository = wordCategoryRepository;
    }

    public WordCategory create(WordCategory wordCategory) {
        return wordCategoryRepository.save(wordCategory);
    }

    public List<WordCategory> list() {
        return wordCategoryRepository.findAll();
    }

    public WordCategory get(Long wordCategoryId) {
        return wordCategoryRepository.findById(wordCategoryId).orElseThrow();
    }

    public WordCategory update(WordCategory wordCategory) {
        if (!wordCategoryRepository.existsById(wordCategory.getId())) throw new NoSuchElementException();
        return wordCategoryRepository.save(wordCategory);
    }

    public void delete(Long wordCategoryId) {
        if (!wordCategoryRepository.existsById(wordCategoryId)) throw new NoSuchElementException();
        wordCategoryRepository.deleteById(wordCategoryId);
    }
}