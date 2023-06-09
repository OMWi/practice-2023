package org.example.service;

import org.example.dto.meaning.MeaningCreationDto;
import org.example.dto.meaning.MeaningDto;
import org.example.model.Meaning;
import org.example.repository.DifficultyRepository;
import org.example.repository.MeaningRepository;
import org.example.repository.WordRepository;
import org.example.utils.ConverterDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class MeaningService {
    private final MeaningRepository meaningRepository;
    private final WordRepository wordRepository;
    private final DifficultyRepository difficultyRepository;

    public MeaningService(MeaningRepository meaningRepository, WordRepository wordRepository, DifficultyRepository difficultyRepository) {
        this.meaningRepository = meaningRepository;
        this.wordRepository = wordRepository;
        this.difficultyRepository = difficultyRepository;
    }
    
    public MeaningDto create(MeaningCreationDto meaningDto) {
        var word = wordRepository.findById(meaningDto.getWordId()).orElseThrow();
        var difficulty = difficultyRepository.findById(meaningDto.getDifficultyId()).orElseThrow();

        var meaning = new Meaning(difficulty, meaningDto.getMeaning());
        meaning.setWord(word);

        var createdMeaning = meaningRepository.save(meaning);
        return ConverterDTO.meaningToDto(createdMeaning);
    }

    public List<MeaningDto> list() {
        var meanings = meaningRepository.findAll();

        var meaningDtoList = new ArrayList<MeaningDto>();
        for (Meaning meaning : meanings) {
            meaningDtoList.add(ConverterDTO.meaningToDto(meaning));
        }

        return meaningDtoList;
    }

    public List<MeaningDto> listByWordId(Long wordId) {
        var meanings = meaningRepository.findAllByWord_Id(wordId);

        var meaningDtoList = new ArrayList<MeaningDto>();
        for (Meaning meaning : meanings) {
            meaningDtoList.add(ConverterDTO.meaningToDto(meaning));
        }

        return meaningDtoList;
    }

    public MeaningDto get(Long meaningId) {
        var meaning = meaningRepository.findById(meaningId).orElseThrow();
        return ConverterDTO.meaningToDto(meaning);
    }

    public MeaningDto update(MeaningCreationDto meaningDto) {
        var meaning = meaningRepository.findById(meaningDto.getId()).orElseThrow();
        var word = wordRepository.findById(meaningDto.getWordId()).orElseThrow();
        var difficulty = difficultyRepository.findById(meaningDto.getDifficultyId()).orElseThrow();
        
        meaning.setMeaning(meaningDto.getMeaning());
        meaning.setWord(word);
        meaning.setDifficulty(difficulty);

        var updatedMeaning = meaningRepository.save(meaning);
        return ConverterDTO.meaningToDto(updatedMeaning);
    }

    public void delete(Long meaningId) {
        if (!meaningRepository.existsById(meaningId)) throw new NoSuchElementException();
        meaningRepository.deleteById(meaningId);
    }
}
