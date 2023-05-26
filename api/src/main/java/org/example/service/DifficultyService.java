package org.example.service;

import org.example.dto.difficulty.DifficultyDto;
import org.example.model.Difficulty;
import org.example.repository.DifficultyRepository;
import org.example.utils.ConverterDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class DifficultyService {
    private final DifficultyRepository difficultyRepository;

    public DifficultyService(DifficultyRepository difficultyRepository) {
        this.difficultyRepository = difficultyRepository;
    }

    public DifficultyDto create(DifficultyDto difficultyDto) {
        var createdDifficulty = difficultyRepository.save(new Difficulty(difficultyDto.getDifficulty()));
        return ConverterDTO.difficultyToDto(createdDifficulty);
    }

    public List<DifficultyDto> list() {
        var difficulties =  difficultyRepository.findAll();

        var difficultyDtoList = new ArrayList<DifficultyDto>();
        for (Difficulty difficulty : difficulties) {
            difficultyDtoList.add(ConverterDTO.difficultyToDto(difficulty));
        }

        return difficultyDtoList;
    }

    public DifficultyDto get(Long difficultyId) {
        var difficulty = difficultyRepository.findById(difficultyId).orElseThrow();
        return ConverterDTO.difficultyToDto(difficulty);
    }

    public DifficultyDto update(DifficultyDto difficultyDto) {
        var difficulty = difficultyRepository.findById(difficultyDto.getId()).orElseThrow();
        difficulty.setDifficulty(difficultyDto.getDifficulty());
        var updatedDifficulty = difficultyRepository.save(difficulty);
        return ConverterDTO.difficultyToDto(updatedDifficulty);
    }

    public void delete(Long difficultyId) {
        if (!difficultyRepository.existsById(difficultyId)) throw new NoSuchElementException();
        difficultyRepository.deleteById(difficultyId);
    }
}
