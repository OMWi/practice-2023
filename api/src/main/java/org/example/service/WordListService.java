package org.example.service;

import org.example.dto.wordlist.WordListCreationDto;
import org.example.dto.wordlist.WordListDto;
import org.example.dto.wordlist.WordListHasWordsDto;
import org.example.model.WordList;
import org.example.repository.DifficultyRepository;
import org.example.repository.UserWordListRepository;
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
    private final UserWordListRepository userWordListRepository;

    public WordListService(WordListRepository wordListRepository, WordRepository wordRepository, DifficultyRepository difficultyRepository, UserWordListRepository userWordListRepository) {
        this.wordListRepository = wordListRepository;
        this.wordRepository = wordRepository;
        this.difficultyRepository = difficultyRepository;
        this.userWordListRepository = userWordListRepository;
    }

    public WordListHasWordsDto create(WordListCreationDto wordListDto) {
        var difficulty = difficultyRepository.findById(wordListDto.getDifficultyId()).orElseThrow();

        var wordList = new WordList(wordListDto.getName(), difficulty);

        for (Long wordId : wordListDto.getWordIdList()) {
            var word = wordRepository.findById(wordId).orElseThrow();
            wordList.addWord(word);
        }

        var createdWordList = wordListRepository.save(wordList);
        var popularity = userWordListRepository.count();
        var likes = userWordListRepository.countByIsFavoriteTrue();
        return ConverterDTO.wordListToDtoWithWords(createdWordList, likes, popularity);
    }

    public List<WordListDto> list() {
        var wordLists = wordListRepository.findAll();

        var wordListDtoList = new ArrayList<WordListDto>();
        for (WordList wordList : wordLists) {
            var popularity = userWordListRepository.count();
            var likes = userWordListRepository.countByIsFavoriteTrue();
            wordListDtoList.add(ConverterDTO.wordListToDto(wordList, likes, popularity));
        }

        return wordListDtoList;
    }

    public WordListHasWordsDto get(Long wordListId) {
        var wordList = wordListRepository.findById(wordListId).orElseThrow();
        var popularity = userWordListRepository.count();
        var likes = userWordListRepository.countByIsFavoriteTrue();
        return ConverterDTO.wordListToDtoWithWords(wordList, likes, popularity);
    }

    public WordListHasWordsDto update(WordListDto wordListDto) {
        var wordList = wordListRepository.findById(wordListDto.getId()).orElseThrow();

        wordList.setName(wordListDto.getName());
        var updatedWordList = wordListRepository.save(wordList);
        var popularity = userWordListRepository.count();
        var likes = userWordListRepository.countByIsFavoriteTrue();
        return ConverterDTO.wordListToDtoWithWords(updatedWordList, likes, popularity);
    }

    public void delete(Long wordListId) {
        if (!wordListRepository.existsById(wordListId)) throw new NoSuchElementException();
        wordListRepository.deleteById(wordListId);
    }
}
