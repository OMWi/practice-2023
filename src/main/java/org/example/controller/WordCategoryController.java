package org.example.controller;

import org.example.model.WordCategory;
import org.example.service.WordCategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/v1/word-categories")
public class WordCategoryController {
    private final WordCategoryService wordCategoryService;

    public WordCategoryController(WordCategoryService wordCategoryService) {
        this.wordCategoryService = wordCategoryService;
    }

    @GetMapping
    public List<WordCategory> listWordCategories() {
        return wordCategoryService.list();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public WordCategory createWordCategory(@RequestBody WordCategory wordCategory) {
        return wordCategoryService.create(wordCategory);
    }

    @GetMapping("/{wordCategoryId}")
    public WordCategory getWordCategory(@PathVariable("wordCategoryId") Long wordCategoryId) {
        try {
            return wordCategoryService.get(wordCategoryId);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping
    public WordCategory updateWordCategory(@RequestBody WordCategory wordCategory) {
        try {
            return wordCategoryService.update(wordCategory);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{wordCategoryId}")
    public void deleteWordCategory(@PathVariable("wordCategoryId") Long wordCategoryId) {
        try {
            wordCategoryService.delete(wordCategoryId);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
