package org.example.controller;

import org.example.dto.MeaningDto;
import org.example.model.Meaning;
import org.example.service.MeaningService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/v1")
public class MeaningController {
    private final MeaningService meaningService;

    public MeaningController(MeaningService meaningService) {
        this.meaningService = meaningService;
    }

    @GetMapping("/words/{wordId}/meanings")
    public List<Meaning> listMeanings(@PathVariable("wordId") Long wordId) {
        return meaningService.list(wordId);
    }

    @PostMapping("/meanings")
    @ResponseStatus(HttpStatus.CREATED)
    public Meaning createMeaning(@RequestBody MeaningDto meaningDto) {
        return meaningService.create(meaningDto);
    }

    @GetMapping("/meanings/{meaningId}")
    public Meaning getMeaning(@PathVariable("meaningId") Long meaningId) {
        try {
            return meaningService.get(meaningId);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/meanings")
    public Meaning updateMeaning(@RequestBody Meaning meaning) {
        try {
            return meaningService.update(meaning);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/meanings/{meaningId}")
    public void deleteMeaning(@PathVariable("meaningId") Long meaningId) {
        try {
            meaningService.delete(meaningId);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
