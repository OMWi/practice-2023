package org.example.controller;

import org.example.dto.word.*;
import org.example.service.WordCategoryService;
import org.example.service.WordService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/words")
public class WordController {
    private final WordService wordService;

    public WordController(WordService wordService, WordCategoryService wordCategoryService) {
        this.wordService = wordService;
    }

    @PostMapping
    @Secured({"ADMIN"})
    @ResponseStatus(HttpStatus.CREATED)
    public WordHasMeaningsDto createWord(@RequestBody WordCreationDto wordDto) {
        return wordService.create(wordDto);
    }

    @GetMapping
    public WordPageDto listWords(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String search
    ) {
        Pageable paging = PageRequest.of(page, size);
        return wordService.list(paging);
    }

    @GetMapping("/{wordId}")
    public WordHasMeaningsDto getWord(@PathVariable("wordId") Long wordId) {
        try {
            return wordService.get(wordId);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping
    @Secured({"ADMIN"})
    public WordHasMeaningsDto updateWord(@RequestBody WordUpdationDto wordDto) {
        try {
            return wordService.update(wordDto);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{wordId}")
    @Secured({"ADMIN"})
    public void deleteWord(@PathVariable("wordId") Long wordId) {
        try {
            wordService.delete(wordId);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
