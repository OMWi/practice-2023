package org.example.controller;

import org.example.dto.WordCreationDto;
import org.example.dto.WordDto;
import org.example.dto.WordUpdationDto;
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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public WordDto createWord(@RequestBody WordCreationDto wordDto) {
        return wordService.create(wordDto);
    }

    @GetMapping
    public List<WordDto> listWords() {
        return wordService.list();
    }

    @GetMapping("/{wordId}")
    public WordDto getWord(@PathVariable("wordId") Long wordId) {
        try {
            return wordService.get(wordId);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping
    public WordDto updateWord(@RequestBody WordUpdationDto wordDto) {
        try {
            return wordService.update(wordDto);
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
