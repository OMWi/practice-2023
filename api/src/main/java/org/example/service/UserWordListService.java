package org.example.service;

import org.example.dto.userwordlist.UserWordListCreationDto;
import org.example.dto.wordlist.UserWordListDto;
import org.example.model.UserdataWordlist;
import org.example.model.UserdataWordlistId;
import org.example.repository.UserDataRepository;
import org.example.repository.UserWordListRepository;
import org.example.repository.WordListRepository;
import org.example.utils.ConverterDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserWordListService {
    private final UserWordListRepository userWordListRepository;
    private final UserDataRepository userDataRepository;
    private final WordListRepository wordListRepository;

    public UserWordListService(UserWordListRepository userWordListRepository, UserDataRepository userDataRepository, WordListRepository wordListRepository) {
        this.userWordListRepository = userWordListRepository;
        this.userDataRepository = userDataRepository;
        this.wordListRepository = wordListRepository;
    }

    public UserWordListDto create(Long userId, UserWordListCreationDto userWordListCreationDto) {
        var userData = userDataRepository.findById(userId).orElseThrow();
        var wordList = wordListRepository.findById(userWordListCreationDto.getWordListId()).orElseThrow();

        var userWord = new UserdataWordlist(userData, wordList);
        if (userWordListCreationDto.getIsFavorite() != null) {
            userWord.setIsFavorite(userWordListCreationDto.getIsFavorite());
        }
        var createdUserWordList = userWordListRepository.save(userWord);

        return ConverterDTO.userWordListToDto(createdUserWordList);
    }

    public List<UserWordListDto> listByUserId(Long userId) {
        var userWordLists = userWordListRepository.findAllByUserData_Id(userId);

        var userWordsDtoList = new ArrayList<UserWordListDto>();
        for (UserdataWordlist userWordList : userWordLists) {
            userWordsDtoList.add(ConverterDTO.userWordListToDto(userWordList));
        }

        return userWordsDtoList;
    }

    public UserWordListDto get(Long userId, Long wordListId) {
        var userWordListId = new UserdataWordlistId(userId, wordListId);
        var userWordList = userWordListRepository.findById(userWordListId).orElseThrow();

        return ConverterDTO.userWordListToDto(userWordList);
    }

    public UserWordListDto update(Long userId, UserWordListCreationDto userWordListDto) {
        var userWordListId = new UserdataWordlistId(userId, userWordListDto.getWordListId());
        var userWordList = userWordListRepository.findById(userWordListId).orElseThrow();

        userWordList.setIsFavorite(userWordListDto.getIsFavorite());
        var updatedUserWord = userWordListRepository.save(userWordList);

        return ConverterDTO.userWordListToDto(updatedUserWord);
    }

    public void delete(Long userId, Long wordListId) {
        var userWordListId = new UserdataWordlistId(userId, wordListId);
        if (!userWordListRepository.existsById(userWordListId)) throw new NoSuchElementException();
        userWordListRepository.deleteById(userWordListId);
    }
}
