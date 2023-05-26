package org.example.service;

import org.example.dto.meaning.MeaningCreationDto;
import org.example.dto.word.*;
import org.example.model.Meaning;
import org.example.model.Word;
import org.example.repository.DifficultyRepository;
import org.example.repository.WordCategoryRepository;
import org.example.repository.WordRepository;
import org.example.utils.ConverterDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.NoSuchElementException;

@Service
public class WordService {
    private final WordRepository wordRepository;
    private final WordCategoryRepository wordCategoryRepository;
    private final DifficultyRepository difficultyRepository;

    public WordService(WordRepository wordRepository, WordCategoryRepository wordCategoryRepository, DifficultyRepository difficultyRepository) {
        this.wordRepository = wordRepository;
        this.wordCategoryRepository = wordCategoryRepository;
        this.difficultyRepository = difficultyRepository;
    }

    public WordHasMeaningsDto create(WordCreationDto wordDto) {
        var wordCategory = wordCategoryRepository.findById(wordDto.getCategoryId()).orElseThrow();
        var wordDifficulty = difficultyRepository.findById(wordDto.getDifficultyId()).orElseThrow();

        var word = new Word(wordDto.getWord(), wordCategory, wordDifficulty);
        var meaningDtoList = wordDto.getMeaningDtoList();
        for (MeaningCreationDto meaningDto : meaningDtoList) {
            var meaningDifficulty = difficultyRepository.findById(meaningDto.getDifficultyId()).orElseThrow();
            var meaning = new Meaning(meaningDifficulty, meaningDto.getMeaning());
            word.addMeaning(meaning);
        }

        var createdWord = wordRepository.save(word);
        return ConverterDTO.wordToDtoWithMeanings(createdWord);
    }

    public WordPageDto list(Pageable paging) {
        Page<Word> wordsPage = wordRepository.findAll(paging);

        var wordDtoList = new ArrayList<WordDto>();
        for (Word word : wordsPage.getContent()) {
            wordDtoList.add(ConverterDTO.wordToDto(word));
        }

        var wordPageDto = new WordPageDto();
        wordPageDto.setWords(wordDtoList);
        wordPageDto.setCurrentPage(wordsPage.getNumber());
        wordPageDto.setTotalItems(wordsPage.getTotalElements());
        wordPageDto.setTotalPages(wordsPage.getTotalPages());

        return wordPageDto;
    }

    public WordPageDto listByUserId(Long userId, Pageable paging) {
        Page<Word> wordsPage = wordRepository.findAllByUsers_UserDataId(userId, paging);

        var wordDtoList = new ArrayList<WordDto>();
        for (Word word : wordsPage.getContent()) {
            wordDtoList.add(ConverterDTO.wordToDto(word));
        }

        var wordPageDto = new WordPageDto();
        wordPageDto.setWords(wordDtoList);
        wordPageDto.setCurrentPage(wordsPage.getNumber());
        wordPageDto.setTotalItems(wordsPage.getTotalElements());
        wordPageDto.setTotalPages(wordsPage.getTotalPages());

        return wordPageDto;
    }

    public WordHasMeaningsDto get(Long wordId) {
        var word = wordRepository.findById(wordId).orElseThrow();
        return ConverterDTO.wordToDtoWithMeanings(word);
    }

    public WordHasMeaningsDto update(WordUpdationDto wordDto) {
        var word = wordRepository.findById(wordDto.getId()).orElseThrow();
        var newCategory = wordCategoryRepository.findById(wordDto.getCategoryId()).orElseThrow();

        word.setCategory(newCategory);
        word.setWord(wordDto.getWord());
        var updatedWord = wordRepository.save(word);
        return ConverterDTO.wordToDtoWithMeanings(updatedWord);
    }

    public void delete(Long wordId) {
        if (!wordRepository.existsById(wordId)) throw new NoSuchElementException();
        wordRepository.deleteById(wordId);
    }
}
