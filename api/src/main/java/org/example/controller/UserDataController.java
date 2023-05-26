package org.example.controller;

import org.example.dto.userdata.UserDataCreationDto;
import org.example.dto.userdata.UserDataDto;
import org.example.dto.userdata.UserDataHaveRowNumberDto;
import org.example.dto.userdata.UserDataUpdationDto;
import org.example.dto.userword.UserWordDto;
import org.example.dto.userword.UserWordHasMeaningsDto;
import org.example.dto.userword.UserWordIdDto;
import org.example.dto.userword.UserWordUpdationDto;
import org.example.dto.wordlist.WordListDto;
import org.example.service.UserDataService;
import org.example.service.UserWordService;
import org.example.service.WordListService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/users-data")
public class UserDataController {
    private final UserDataService userDataService;
    private final WordListService wordListService;
    private final UserWordService userWordService;

    public UserDataController(UserDataService userDataService, WordListService wordListService, UserWordService userWordService) {
        this.userDataService = userDataService;
        this.wordListService = wordListService;
        this.userWordService = userWordService;
    }

    @PostMapping
    @Secured({"ADMIN"})
    @ResponseStatus(HttpStatus.CREATED)
    public UserDataDto createUserData(@RequestBody UserDataCreationDto userDataDto) {
        return userDataService.create(userDataDto);
    }

    @GetMapping
    @Secured({"ADMIN"})
    public List<UserDataDto> listUsersData() {
        return userDataService.list();
    }

    @GetMapping("/leaderboard")
    public List<UserDataHaveRowNumberDto> topUsers() {
        return userDataService.getLeaderboard();
    }

    @GetMapping("/{userId}")
    @Secured({"ADMIN", "USER"})
    public UserDataDto getUserData(@PathVariable("userId") Long userId) {
        try {
            return userDataService.get(userId);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping
    @Secured({"ADMIN"})
    public UserDataDto updateUserData(@RequestBody UserDataUpdationDto userDataDto) {
        try {
            return userDataService.update(userDataDto);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{userId}")
    @Secured({"ADMIN"})
    public void deleteUserData(@PathVariable("userId") Long userId) {
        try {
            userDataService.delete(userId);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping("{userId}/words/{wordId}")
    @Secured({"ADMIN", "USER"})
    public UserWordDto addUserWord(@PathVariable("userId") Long userId, @PathVariable("wordId") Long wordId) {
        return userWordService.create(userId, wordId);
    }

    @GetMapping("/{userId}/words")
    @Secured({"ADMIN", "USER"})
    public List<UserWordDto> listUserWords(@PathVariable("userId") Long userId) {
        return userWordService.listByUserId(userId);
    }

    @GetMapping("/{userId}/words/{wordId}")
    @Secured({"ADMIN", "USER"})
    public UserWordHasMeaningsDto getUserWord(@PathVariable("userId") Long userId, @PathVariable("wordId") Long wordId) {
        try {
            return userWordService.get(userId, wordId);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("{userId}/words/random")
    @Secured({"ADMIN", "USER"})
    public UserWordHasMeaningsDto getRandomNotLearnedWord(@PathVariable("userId") Long userId) {
        try {
            return userWordService.getRandomNotLearned(userId);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{userId}/words")
    @Secured({"ADMIN", "USER"})
    public UserWordDto updateUserWord(@PathVariable("userId") Long userId, @RequestBody UserWordUpdationDto userWordDto) {
        try {
            return userWordService.update(userId, userWordDto);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{userId}/words/inc")
    @Secured({"ADMIN", "USER"})
    public UserWordDto incUserWordGuessStreak(@PathVariable("userId") Long userId, @RequestBody UserWordIdDto userWordDto) {
        try {
            return userWordService.incGuessStreak(userId, userWordDto);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{userId}/words/learned")
    @Secured({"ADMIN", "USER"})
    public UserWordDto setUserWordLearned(@PathVariable("userId") Long userId, @RequestBody UserWordIdDto userWordDto) {
        try {
            return userWordService.setLearned(userId, userWordDto);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{userId}/words/{wordId}")
    @Secured({"ADMIN", "USER"})
    public void deleteUserWord(@PathVariable("userId") Long userId, @PathVariable("wordId") Long wordId) {
        try {
            userWordService.delete(userId, wordId);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping("/{userId}/word-lists/{wordListId}")
    @Secured({"ADMIN", "USER"})
    public void addUserWordList(@PathVariable("userId") Long userId, @PathVariable Long wordListId) {
        try {
            userDataService.addWordList(userId, wordListId);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{userId}/word-lists")
    @Secured({"ADMIN", "USER"})
    public List<WordListDto> listUserWordLists(@PathVariable("userId") Long userId) {
        return wordListService.listByUserid(userId);
    }

    @GetMapping("/{userId}/word-lists/{wordListId}")
    @Secured({"ADMIN", "USER"})
    public WordListDto getWordList(@PathVariable("userId") Long userId, @PathVariable("wordListId") Long wordListId) {
        try {
            return userDataService.getWordList(userId, wordListId);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{userId}/word-lists/{wordListId}")
    @Secured({"ADMIN", "USER"})
    public void deleteUserWordList(@PathVariable("userId") Long userId, @PathVariable("wordListId") Long wordListId) {
        try {
            userDataService.deleteWordList(userId, wordListId);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}

