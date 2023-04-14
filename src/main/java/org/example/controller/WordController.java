package org.example.controller;

import org.example.model.Word;
import org.example.service.WordCategoryService;
import org.example.service.WordService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/v1/words")
public class WordController {
    private final WordService wordService;

    public WordController(WordService wordService, WordCategoryService wordCategoryService) {
        this.wordService = wordService;
    }

    @GetMapping
    public List<Word> listWords() {
        return wordService.list();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Word createWord(@RequestBody Word word) {
        System.out.println("create word request");
        return wordService.create(word);
    }

    @GetMapping("/{wordId}")
    public Word getWord(@PathVariable("wordId") Long wordId) {
        try {
            return wordService.get(wordId);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping
    public Word updateWord(@RequestBody Word word) {
        try {
            return wordService.update(word);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{wordId}")
    public void deleteWord(@PathVariable("wordId") Long wordId) {
        try {
            wordService.delete(wordId);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
