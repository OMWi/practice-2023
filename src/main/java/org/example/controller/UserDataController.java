package org.example.controller;

import org.example.dto.userdata.UserDataCreationDto;
import org.example.dto.userdata.UserDataDto;
import org.example.dto.userdata.UserDataUpdationDto;
import org.example.dto.word.WordDto;
import org.example.dto.wordlist.WordListDto;
import org.example.service.UserDataService;
import org.example.service.WordListService;
import org.example.service.WordService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDataDto createUserData(@RequestBody UserDataCreationDto userDataDto) {
        return userDataService.create(userDataDto);
    }

    @GetMapping
    public List<UserDataDto> listUsersData() {
        return userDataService.list();
    }

    @GetMapping("/{userDataId}")
    public UserDataDto getUserData(@PathVariable("userDataId") Long userDataId) {
        try {
            return userDataService.get(userDataId);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping
    public UserDataDto updateUserData(@RequestBody UserDataUpdationDto userDataDto) {
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
    public List<WordDto> listUserWords(@PathVariable("userDataId") Long userId) {
//        return wordService.listByUser(userId);
        // todo: implement
        return new ArrayList<>();
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
    public List<WordListDto> listUserWordLists(@PathVariable("userDataId") Long userId) {
//        return wordListService.listByUser(userId);
        // todo: implement
        return new ArrayList<>();
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
