package org.example.controller;

import org.example.dto.word.WordCreationDto;
import org.example.dto.word.WordDto;
import org.example.dto.word.WordUpdationDto;
import org.example.dto.wordlist.WordListCreationDto;
import org.example.dto.wordlist.WordListDto;
import org.example.dto.wordlist.WordListHasWordsDto;
import org.example.service.WordListService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/v1")
public class WordListController {
    private final WordListService wordListService;

    public WordListController(WordListService wordListService) {
        this.wordListService = wordListService;
    }

    @PostMapping("/word-lists")
    @ResponseStatus(HttpStatus.CREATED)
    public WordListHasWordsDto createWordList(@RequestBody WordListCreationDto wordListDto) {
        return wordListService.create(wordListDto);
    }

    @GetMapping("/word-lists")
    public List<WordListDto> listWordLists() {
        return wordListService.list();
    }

    @GetMapping("/users-data/{userid}/word-lists")
    public List<WordListDto> listWordListsByUserId(@PathVariable("userId") Long userId) {
        return wordListService.listByUserid(userId);
    }

    @GetMapping("/word-lists/{wordListId}")
    public WordListHasWordsDto getWordList(@PathVariable("wordListId") Long wordListId) {
        try {
            return wordListService.get(wordListId);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/word-lists")
    public WordListHasWordsDto updateWordList(@RequestBody WordListDto wordListDto) {
        try {
            return wordListService.update(wordListDto);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/word-lists/{wordListId}")
    public void deleteWordList(@PathVariable("wordListId") Long wordListId) {
        try {
            wordListService.delete(wordListId);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
