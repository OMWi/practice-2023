package org.example.service;

import org.example.dto.wordlist.WordListCreationDto;
import org.example.dto.wordlist.WordListDto;
import org.example.dto.wordlist.WordListHasWordsDto;
import org.example.model.WordList;
import org.example.repository.DifficultyRepository;
import org.example.repository.WordListRepository;
import org.example.repository.WordRepository;
import org.example.utils.ConverterDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class WordListService {
    private final WordListRepository wordListRepository;
    private final WordRepository wordRepository;
    private final DifficultyRepository difficultyRepository;

    public WordListService(WordListRepository wordListRepository, WordRepository wordRepository, DifficultyRepository difficultyRepository) {
        this.wordListRepository = wordListRepository;
        this.wordRepository = wordRepository;
        this.difficultyRepository = difficultyRepository;
    }

    public WordListHasWordsDto create(WordListCreationDto wordListDto) {
        var difficulty = difficultyRepository.findById(wordListDto.getDifficultyId()).orElseThrow();

        var wordList = new WordList(wordListDto.getName(), difficulty);

        for (Long wordId : wordListDto.getWordIdList()) {
            var word = wordRepository.findById(wordId).orElseThrow();
            wordList.addWord(word);
        }

        var createdWordList = wordListRepository.save(wordList);
        return ConverterDTO.wordListToDtoWithWords(createdWordList);
    }

    public List<WordListDto> list() {
        var wordLists = wordListRepository.findAll();

        var wordListDtoList = new ArrayList<WordListDto>();
        for (WordList wordList : wordLists) {
            wordListDtoList.add(ConverterDTO.wordListToDto(wordList));
        }

        return wordListDtoList;
    }

    public List<WordListDto> listByUserid(Long userId) {
        var wordLists = wordListRepository.findAllByUserDataList_Id(userId);

        var wordListDtoList = new ArrayList<WordListDto>();
        for (WordList wordList : wordLists) {
            wordListDtoList.add(ConverterDTO.wordListToDto(wordList));
        }

        return wordListDtoList;
    }

    public WordListHasWordsDto get(Long wordListId) {
        var wordList = wordListRepository.findById(wordListId).orElseThrow();
        return ConverterDTO.wordListToDtoWithWords(wordList);
    }

    public WordListHasWordsDto update(WordListDto wordListDto) {
        var wordList = wordListRepository.findById(wordListDto.getId()).orElseThrow();

        wordList.setName(wordListDto.getName());
        wordList.setPopularity(wordListDto.getPopularity());

        var updatedWordList = wordListRepository.save(wordList);
        return ConverterDTO.wordListToDtoWithWords(updatedWordList);
    }

    public void delete(Long wordListId) {
        if (!wordListRepository.existsById(wordListId)) throw new NoSuchElementException();
        wordListRepository.deleteById(wordListId);
    }
}
