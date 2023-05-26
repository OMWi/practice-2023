package org.example.controller;

import org.example.dto.difficulty.DifficultyDto;
import org.example.service.DifficultyService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/difficulties")
public class DifficultyController {
    private final DifficultyService difficultyService;

    public DifficultyController(DifficultyService difficultyService) {
        this.difficultyService = difficultyService;
    }

    @PostMapping
    @Secured({"ADMIN"})
    @ResponseStatus(HttpStatus.CREATED)
    public DifficultyDto createDifficulty(@RequestBody DifficultyDto difficultyDto) {
        return difficultyService.create(difficultyDto);
    }

    @GetMapping
    public List<DifficultyDto> listWordCategories() {
        return difficultyService.list();
    }

    @GetMapping("/{difficultyId}")
    public DifficultyDto getDifficulty(@PathVariable("difficultyId") Long difficultyId) {
        try {
            return difficultyService.get(difficultyId);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping
    @Secured({"ADMIN"})
    public DifficultyDto updateDifficulty(@RequestBody DifficultyDto difficultyDto) {
        try {
            return difficultyService.update(difficultyDto);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{difficultyId}")
    @Secured({"ADMIN"})
    public void deleteDifficulty(@PathVariable("difficultyId") Long difficultyId) {
        try {
            difficultyService.delete(difficultyId);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
