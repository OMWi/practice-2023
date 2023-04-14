package org.example.service;

import org.example.dto.MeaningDto;
import org.example.model.Meaning;
import org.example.repository.MeaningRepository;
import org.example.repository.WordRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class MeaningService {
    private final MeaningRepository meaningRepository;
    private final WordRepository wordRepository;

    public MeaningService(MeaningRepository meaningRepository, WordRepository wordRepository) {
        this.meaningRepository = meaningRepository;
        this.wordRepository = wordRepository;
    }

    public Meaning create(MeaningDto meaningDto) {
        var word = wordRepository.findById(meaningDto.wordId).orElseThrow();
        var meaning = new Meaning(meaningDto.level, meaningDto.text);
        meaning.setWord(word);
//        word.addMeaning(meaning);
//        wordRepository.save(word);
        return meaningRepository.save(meaning);
    }

    public List<Meaning> list(Long wordId) {
        return meaningRepository.findAllByWord_Id(wordId);
    }

    public Meaning get(Long meaningId) {
        return meaningRepository.findById(meaningId).orElseThrow();
    }

    public Meaning update(Meaning meaning) {
        if (!meaningRepository.existsById(meaning.getId())) throw new NoSuchElementException();
        return meaningRepository.save(meaning);
    }

    public void delete(Long meaningId) {
        if (!meaningRepository.existsById(meaningId)) throw new NoSuchElementException();
        meaningRepository.deleteById(meaningId);
    }
}
