package org.example.controller;

import jakarta.annotation.security.RolesAllowed;
import org.example.dto.meaning.MeaningDto;
import org.example.service.MeaningService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
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

    @PostMapping("/meanings")
    @Secured({"ADMIN"})
    @ResponseStatus(HttpStatus.CREATED)
    public MeaningDto createMeaning(@RequestBody MeaningDto meaningDto) {
        return meaningService.create(meaningDto);
    }

    @GetMapping("/meanings")
    public List<MeaningDto> listMeanings() {
        return meaningService.list();
    }

    @GetMapping("/words/{wordId}/meanings")
    public List<MeaningDto> listMeaningsByWordId(@PathVariable("wordId") Long wordId) {
        return meaningService.listByWordId(wordId);
    }

    @GetMapping("/meanings/{meaningId}")
    public MeaningDto getMeaning(@PathVariable("meaningId") Long meaningId) {
        try {
            return meaningService.get(meaningId);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/meanings")
    @Secured({"ADMIN"})
    public MeaningDto updateMeaning(@RequestBody MeaningDto meaning) {
        try {
            return meaningService.update(meaning);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/meanings/{meaningId}")
    @Secured({"ADMIN"})
    public void deleteMeaning(@PathVariable("meaningId") Long meaningId) {
        try {
            meaningService.delete(meaningId);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
