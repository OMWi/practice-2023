package org.example.controller;

import org.example.dto.userdata.UserDataCreationDto;
import org.example.dto.userdata.UserDataDto;
import org.example.dto.userdata.UserDataUpdationDto;
import org.example.dto.userword.UserWordCreationDto;
import org.example.dto.userword.UserWordDto;
import org.example.dto.userword.UserWordUpdationDto;
import org.example.dto.word.WordDto;
import org.example.dto.wordlist.UserWordListDto;
import org.example.dto.wordlist.WordListDto;
import org.example.service.UserDataService;
import org.example.service.UserWordService;
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
    private final UserWordService userWordService;

    public UserDataController(UserDataService userDataService, WordService wordService, WordListService wordListService, UserWordService userWordService) {
        this.userDataService = userDataService;
        this.wordService = wordService;
        this.wordListService = wordListService;
        this.userWordService = userWordService;
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

    @PostMapping("{userDataId}/words")
    public UserWordDto addUserWord(@PathVariable("userDataId") Long userId, @RequestBody UserWordCreationDto userWordDto) {
        userWordDto.setUserId(userId);
        return userWordService.create(userWordDto);
    }

    @GetMapping("/{userDataId}/words")
    public List<UserWordDto> listUserWords(@PathVariable("userDataId") Long userId) {
        return userWordService.listByUserId(userId);
    }

    @PutMapping("/{userDataId}/words")
    public UserWordDto updateUserWord(@PathVariable("userDataId") Long userId, @RequestBody UserWordUpdationDto userWordDto) {
        try {
            return userWordService.update(userWordDto);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{userDataId}/words/{wordId}")
    public void deleteUserWord(@PathVariable("userDataId") Long userId, @PathVariable("wordId") Long wordId) {
        try {
            userWordService.delete(userId, wordId);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping("/{userDataId}/word-lists")
    public void addUserWordList(@PathVariable("userDataId") Long userId, @RequestBody UserWordListDto userWordListDto) {
        try {
            userWordListDto.setUserId(userId);
            userDataService.addWordList(userWordListDto);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{userDataId}/word-lists")
    public List<WordListDto> listUserWordLists(@PathVariable("userDataId") Long userId) {
        return wordListService.listByUserid(userId);
    }

    @DeleteMapping("/{userDataId}/word-lists/{wordListId}")
    public void deleteUserWordList(@PathVariable("userDataId") Long userId, @PathVariable("wordListId") Long wordListId) {
        try {
            userDataService.deleteWordList(userId, wordListId);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
