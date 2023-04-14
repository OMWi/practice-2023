package org.example.service;

import org.example.model.Meaning;
import org.example.repository.MeaningRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class MeaningService {
    private final MeaningRepository meaningRepository;

    public MeaningService(MeaningRepository meaningRepository) {
        this.meaningRepository = meaningRepository;
    }

    public Meaning create(Meaning meaning) {
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
