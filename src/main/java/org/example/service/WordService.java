package org.example.service;

import org.example.dto.MeaningDto;
import org.example.dto.WordCreationDto;
import org.example.dto.WordDto;
import org.example.dto.WordUpdationDto;
import org.example.model.Meaning;
import org.example.model.Word;
import org.example.repository.WordCategoryRepository;
import org.example.repository.WordRepository;
import org.example.utils.ConverterDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class WordService {
    private final WordRepository wordRepository;
    private final WordCategoryRepository wordCategoryRepository;

    public WordService(WordRepository wordRepository, WordCategoryRepository wordCategoryRepository) {
        this.wordRepository = wordRepository;
        this.wordCategoryRepository = wordCategoryRepository;
    }

    public WordDto create(WordCreationDto wordDto) {
        var wordCategory = wordCategoryRepository.findById(wordDto.getCategoryId()).orElseThrow();

        var word = new Word(wordDto.getText(), wordCategory);
        var meaningDtoList = wordDto.getMeaningDtoList();
        for (MeaningDto meaningDto : meaningDtoList) {
            var meaning = new Meaning(meaningDto.getLevel(), meaningDto.getText());
            word.addMeaning(meaning);
        }

        var createdWord = wordRepository.save(word);
        return ConverterDTO.wordToDto(createdWord);
    }

    public List<WordDto> list() {
        var words = wordRepository.findAll();

        var wordDtoList = new ArrayList<WordDto>();
        for (Word word : words) {
            wordDtoList.add(ConverterDTO.wordToDto(word));
        }

        return wordDtoList;
    }

    public WordDto get(Long wordId) {
        var word = wordRepository.findById(wordId).orElseThrow();
        return ConverterDTO.wordToDto(word);
    }

    public WordDto update(WordUpdationDto wordDto) {
        var word = wordRepository.findById(wordDto.getId()).orElseThrow();
        var newCategory = wordCategoryRepository.findById(wordDto.getCategoryId()).orElseThrow();

        word.setCategory(newCategory);
        word.setText(wordDto.getText());
        var updatedWord = wordRepository.save(word);
        return ConverterDTO.wordToDto(updatedWord);
    }

    public void delete(Long wordId) {
        if (!wordRepository.existsById(wordId)) throw new NoSuchElementException();
        wordRepository.deleteById(wordId);
    }
}