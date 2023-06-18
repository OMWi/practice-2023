package org.example.controller;

import org.example.dto.wordlist.WordListCreationDto;
import org.example.dto.wordlist.WordListDto;
import org.example.dto.wordlist.WordListHasWordsDto;
import org.example.dto.wordlist.WordListPageDto;
import org.example.security.UserDetailsImpl;
import org.example.service.UserDataService;
import org.example.service.WordListService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.NoSuchElementException;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/word-lists")
public class WordListController {
    private final WordListService wordListService;
    private final UserDataService userDataService;

    public WordListController(WordListService wordListService, UserDataService userDataService) {
        this.wordListService = wordListService;
        this.userDataService = userDataService;
    }

    @PostMapping
    @Secured({"ADMIN", "USER"})
    @ResponseStatus(HttpStatus.CREATED)
    public WordListHasWordsDto createWordList(@RequestBody WordListCreationDto wordListDto) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            String role = userDetails.getAuthorities().stream()
                    .map(item -> item.getAuthority())
                    .toList().get(0);
            if (role != "ADMIN" && !userDataService.isSubscriber(userDetails.getId())) {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN);
            }
            return wordListService.create(wordListDto, userDetails.getId());
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public WordListPageDto listWordLists(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String searchText,
            @RequestParam(required = false) Integer difficultyId,
            @RequestParam(required = false) String sortBy
    ) {
        Pageable paging = PageRequest.of(page, size);
        return wordListService.list(paging, searchText, difficultyId, sortBy);
    }

    @GetMapping("/{wordListId}")
    public WordListHasWordsDto getWordList(@PathVariable("wordListId") Long wordListId) {
        try {
            return wordListService.get(wordListId);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping
    @Secured({"ADMIN"})
    public WordListHasWordsDto updateWordList(@RequestBody WordListDto wordListDto) {
        try {
            return wordListService.update(wordListDto);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{wordListId}")
    @Secured({"ADMIN"})
    public void deleteWordList(@PathVariable("wordListId") Long wordListId) {
        try {
            wordListService.delete(wordListId);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
