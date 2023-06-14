package org.example.service;

import org.example.dto.wordlist.*;
import org.example.model.WordList;
import org.example.repository.*;
import org.example.utils.ConverterDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.NoSuchElementException;

@Service
public class WordListService {
    private final WordListRepository wordListRepository;
    private final WordRepository wordRepository;
    private final DifficultyRepository difficultyRepository;
    private final UserWordListRepository userWordListRepository;
    private final UserDataRepository userDataRepository;

    public WordListService(WordListRepository wordListRepository, WordRepository wordRepository, DifficultyRepository difficultyRepository, UserWordListRepository userWordListRepository, UserDataRepository userDataRepository) {
        this.wordListRepository = wordListRepository;
        this.wordRepository = wordRepository;
        this.difficultyRepository = difficultyRepository;
        this.userWordListRepository = userWordListRepository;
        this.userDataRepository = userDataRepository;
    }

    public WordListHasWordsDto create(WordListCreationDto wordListDto, Long userId) {
        var difficulty = difficultyRepository.findById(wordListDto.getDifficultyId()).orElseThrow();
        var owner = userDataRepository.findById(userId).orElseThrow();

        var wordList = new WordList(wordListDto.getName(), difficulty, owner);

        for (Long wordId : wordListDto.getWordIdList()) {
            var word = wordRepository.findById(wordId).orElseThrow();
            wordList.addWord(word);
        }

        var createdWordList = wordListRepository.save(wordList);
        var popularity = 0L;
        var likes = 0L;
        return ConverterDTO.wordListToDtoWithWords(createdWordList, likes, popularity);
    }

    public WordListPageDto list(Pageable paging, String searchText, Integer difficultyId, String sortBy) {
        Page<WordListProjection> wordListsPage = wordListRepository.findAllByCondition(paging, searchText, difficultyId, sortBy);

        var wordListDtoList = new ArrayList<WordListDto>();
        for (WordListProjection wordListProjection : wordListsPage.getContent()) {
            var owner = userDataRepository.findById(wordListProjection.getOwnerId()).orElseThrow();
            var wordListDto = new WordListDto(
                    wordListProjection.getId(),
                    wordListProjection.getName(),
                    wordListProjection.getDifficulty(),
                    wordListProjection.getLikes(),
                    wordListProjection.getPopularity(),
                    ConverterDTO.userDataToDto(owner)
            );
            wordListDtoList.add(wordListDto);
        }

        var wordListPageDto = new WordListPageDto();
        wordListPageDto.setWordLists(wordListDtoList);
        wordListPageDto.setCurrentPage(wordListsPage.getNumber());
        wordListPageDto.setTotalItems(wordListsPage.getTotalElements());
        wordListPageDto.setTotalPages(wordListsPage.getTotalPages());

        return wordListPageDto;
    }

    public WordListHasWordsDto get(Long wordListId) {
        var wordList = wordListRepository.findById(wordListId).orElseThrow();
        var popularity = userWordListRepository.countByWordList_Id(wordListId);
        var likes = userWordListRepository.countByWordList_IdAndIsFavoriteTrue(wordListId);
        return ConverterDTO.wordListToDtoWithWords(wordList, likes, popularity);
    }

    public WordListHasWordsDto update(WordListDto wordListDto) {
        var wordList = wordListRepository.findById(wordListDto.getId()).orElseThrow();

        wordList.setName(wordListDto.getName());
        var updatedWordList = wordListRepository.save(wordList);
        var popularity = userWordListRepository.countByWordList_Id(wordListDto.getId());
        var likes = userWordListRepository.countByWordList_IdAndIsFavoriteTrue(wordListDto.getId());
        return ConverterDTO.wordListToDtoWithWords(updatedWordList, likes, popularity);
    }

    public void delete(Long wordListId) {
        if (!wordListRepository.existsById(wordListId)) throw new NoSuchElementException();
        wordListRepository.deleteById(wordListId);
    }
}
