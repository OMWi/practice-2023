package org.example.controller;

import org.example.dto.userdata.UserDataCreationDto;
import org.example.dto.userdata.UserDataDto;
import org.example.dto.userdata.UserDataHaveRowNumberDto;
import org.example.dto.userdata.UserDataUpdationDto;
import org.example.dto.userword.*;
import org.example.dto.userwordlist.UserWordListCreationDto;
import org.example.dto.wordlist.UserWordListDto;
import org.example.enums.UserRole;
import org.example.security.UserDetailsImpl;
import org.example.service.UserDataService;
import org.example.service.UserWordListService;
import org.example.service.UserWordService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/users-data")
public class UserDataController {
    private final UserDataService userDataService;
    private final UserWordService userWordService;
    private final UserWordListService userWordListService;

    public UserDataController(UserDataService userDataService, UserWordService userWordService, UserWordListService userWordListService) {
        this.userDataService = userDataService;
        this.userWordService = userWordService;
        this.userWordListService = userWordListService;
    }

    public void confirmUserAuthority(Long userId) throws ResponseStatusException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        String role = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .toList().get(0);
        if (UserRole.valueOf(role) != UserRole.ADMIN && userId != userDetails.getId()) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
    }


//    user data methods
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
    public List<UserDataHaveRowNumberDto> getLeaderboard() {
        return userDataService.getLeaderboard();
    }

    @GetMapping("/{userId}")
    @Secured({"ADMIN", "USER"})
    public UserDataDto getUserData(@PathVariable("userId") Long userId) {
        try {
            confirmUserAuthority(userId);
            return userDataService.get(userId);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{userId}")
    @Secured({"ADMIN", "USER"})
    public UserDataDto updateUserData(@PathVariable("userId") Long userId, @RequestBody UserDataUpdationDto userDataDto) {
        try {
            confirmUserAuthority(userId);
            return userDataService.update(userDataDto);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{userId}")
    @Secured({"ADMIN", "USER"})
    public void deleteUserData(@PathVariable("userId") Long userId) {
        try {
            confirmUserAuthority(userId);
            userDataService.delete(userId);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }


//    user words methods
    @PostMapping("{userId}/words/{wordId}")
    @Secured({"ADMIN", "USER"})
    public UserWordDto addUserWord(@PathVariable("userId") Long userId, @PathVariable("wordId") Long wordId) {
        try {
            confirmUserAuthority(userId);
            return userWordService.create(userId, wordId);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{userId}/words")
    @Secured({"ADMIN", "USER"})
    public List<UserWordDto> listUserWords(@PathVariable("userId") Long userId) {
        confirmUserAuthority(userId);
        return userWordService.listByUserId(userId);
    }

    @GetMapping("/{userId}/words/{wordId}")
    @Secured({"ADMIN", "USER"})
    public UserWordHasMeaningsDto getUserWord(@PathVariable("userId") Long userId, @PathVariable("wordId") Long wordId) {
        try {
            confirmUserAuthority(userId);
            return userWordService.get(userId, wordId);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

//    @PutMapping("/{userId}/words")
//    @Secured({"ADMIN", "USER"})
//    public UserWordDto updateUserWord(@PathVariable("userId") Long userId, @RequestBody UserWordUpdationDto userWordDto) {
//        if (userWordDto.getIsFavorite() == null || userWordDto.getWordId() == null) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
//        }
//
//        try {
//            confirmUserAuthority(userId);
//            return userWordService.update(userId, userWordDto);
//        } catch (NoSuchElementException e) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
//        }
//    }

    @DeleteMapping("/{userId}/words/{wordId}")
    @Secured({"ADMIN", "USER"})
    public void deleteUserWord(@PathVariable("userId") Long userId, @PathVariable("wordId") Long wordId) {
        try {
            confirmUserAuthority(userId);
            userWordService.delete(userId, wordId);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{userId}/words/learn")
    @Secured({"ADMIN", "USER"})
    public UserWordHasMeaningsDto getQuestionWord(@PathVariable("userId") Long userId) {
        try {
            confirmUserAuthority(userId);
            return userWordService.getQuestionWord(userId);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/{userId}/words/learn")
    @Secured({"ADMIN", "USER"})
    public UserWordDto handleQuestionResult(@PathVariable("userId") Long userId, @RequestBody QuestionResultDto questionResultDto) {
        if (questionResultDto.getIsCorrect() == null || questionResultDto.getWordId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        try {
            confirmUserAuthority(userId);
            return userWordService.handleQuestionResult(userId, questionResultDto);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }


//    user word lists methods
    @PostMapping("/{userId}/word-lists")
    @Secured({"ADMIN", "USER"})
    public UserWordListDto addUserWordList(@PathVariable("userId") Long userId, @RequestBody UserWordListCreationDto userWordListCreationDto) {
        if (userWordListCreationDto.getWordListId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        try {
            confirmUserAuthority(userId);
            return userWordListService.create(userId, userWordListCreationDto);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{userId}/word-lists")
    @Secured({"ADMIN", "USER"})
    public List<UserWordListDto> listUserWordLists(@PathVariable("userId") Long userId) {
        confirmUserAuthority(userId);
        return userWordListService.listByUserId(userId);
    }

    @GetMapping("/{userId}/word-lists/{wordListId}")
    @Secured({"ADMIN", "USER"})
    public UserWordListDto getUserWordList(@PathVariable("userId") Long userId, @PathVariable("wordListId") Long wordListId) {
        try {
            confirmUserAuthority(userId);
            return userWordListService.get(userId, wordListId);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{userId}/word-lists")
    @Secured({"ADMIN", "USER"})
    public UserWordListDto updateWordList(@PathVariable("userId") Long userId, @RequestBody UserWordListCreationDto userWordListDto) {
        if (userWordListDto.getIsFavorite() == null || userWordListDto.getWordListId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        try {
            confirmUserAuthority(userId);
            return userWordListService.update(userId, userWordListDto);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{userId}/word-lists/{wordListId}")
    @Secured({"ADMIN", "USER"})
    public void deleteUserWordList(@PathVariable("userId") Long userId, @PathVariable("wordListId") Long wordListId) {
        try {
            confirmUserAuthority(userId);
            userWordListService.delete(userId, wordListId);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}

