package org.example.controller;

import org.example.dto.UserDataDto;
import org.example.dto.WordDto;
import org.example.model.UserData;
import org.example.model.Word;
import org.example.model.WordList;
import org.example.service.UserDataService;
import org.example.service.WordListService;
import org.example.service.WordService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/v1/user-data")
public class UserDataController {
    private final UserDataService userDataService;
    private final WordService wordService;
    private final WordListService wordListService;

    public UserDataController(UserDataService userDataService, WordService wordService, WordListService wordListService) {
        this.userDataService = userDataService;
        this.wordService = wordService;
        this.wordListService = wordListService;
    }

    @GetMapping
    public List<UserData> listUserData() {
        return userDataService.list();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserData createUserData(@RequestBody UserDataDto userDataDto) {
        System.out.println("create userData request");
        return userDataService.create(userDataDto);
    }

    @GetMapping("/{userDataId}")
    public UserData getUserData(@PathVariable("userDataId") Long userDataId) {
        try {
            return userDataService.get(userDataId);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping
    public UserData updateUserData(@RequestBody UserDataDto userDataDto) {
        try {
            return userDataService.update(userDataDto);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{userDataId}")
    public void deleteUserData(@PathVariable("userDataId") Long userDataId) {
        try {
            userDataService.delete(userDataId);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{userDataId}/words")
    public List<Word> listUserWords(@PathVariable("userDataId") Long userId) {
        return wordService.listByUser(userId);
    }

    @PostMapping("/{userDataId}/words")
    public void addUserWord(@PathVariable("userDataId") Long userId, @RequestBody WordDto wordDto) {
        // todo: implement add word to user(new word to learn)
    }

    @PutMapping("/{userDataId}/words")
    public void updateUserWord(@PathVariable("userDataId") Long userId, @RequestBody WordDto word) {
        // todo: implement change user word status(word learned for example)
    }

    @DeleteMapping("/{userDataId}/words/{wordId}")
    public void deleteUserWord(@PathVariable("userDataId") Long userId, @PathVariable("wordId") Long wordId) {
        // todo: implement delete word from user's list
    }


    @GetMapping("/{userDataId}/word-lists")
    public List<WordList> listUserWordLists(@PathVariable("userDataId") Long userId) {
        return wordListService.listByUser(userId);
    }

    @PostMapping("/{userDataId}/word-lists")
    public void addUserWordList(@PathVariable("userDataId") Long userId, @RequestBody WordDto wordDto) {
        // todo: implement add wordlist to user
    }

    @DeleteMapping("/{userDataId}/word-lists/{wordListId}")
    public void deleteUserWordList(@PathVariable("userDataId") Long userId, @PathVariable("wordListId") Long wordListId) {
        // todo: implement wordlist deletion from user
    }
}
