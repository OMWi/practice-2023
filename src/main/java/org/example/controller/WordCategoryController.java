package org.example.controller;

import org.example.dto.wordcategory.WordCategoryDto;
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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public WordCategoryDto createWordCategory(@RequestBody WordCategoryDto wordCategoryDto) {
        return wordCategoryService.create(wordCategoryDto);
    }

    @GetMapping
    public List<WordCategoryDto> listWordCategories() {
        return wordCategoryService.list();
    }

    @GetMapping("/{wordCategoryId}")
    public WordCategoryDto getWordCategory(@PathVariable("wordCategoryId") Long wordCategoryId) {
        try {
            return wordCategoryService.get(wordCategoryId);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping
    public WordCategoryDto updateWordCategory(@RequestBody WordCategoryDto wordCategoryDto) {
        try {
            return wordCategoryService.update(wordCategoryDto);
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
