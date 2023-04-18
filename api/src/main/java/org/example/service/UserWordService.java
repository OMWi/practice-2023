package org.example.service;

import org.example.dto.userword.UserWordDto;
import org.example.dto.userword.UserWordHasMeaningsDto;
import org.example.dto.userword.UserWordIdDto;
import org.example.dto.userword.UserWordUpdationDto;
import org.example.model.UserdataWord;
import org.example.model.UserdataWordId;
import org.example.repository.UserDataRepository;
import org.example.repository.UserWordRepository;
import org.example.repository.WordRepository;
import org.example.utils.ConverterDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserWordService {
    private final UserWordRepository userWordRepository;
    private final UserDataRepository userDataRepository;
    private final WordRepository wordRepository;

    public UserWordService(UserWordRepository userWordRepository, UserDataRepository userDataRepository, WordRepository wordRepository) {
        this.userWordRepository = userWordRepository;
        this.userDataRepository = userDataRepository;
        this.wordRepository = wordRepository;
    }

    public UserWordDto create(Long userId, Long wordId) {
        var userData = userDataRepository.findById(userId).orElseThrow();
        var word = wordRepository.findById(wordId).orElseThrow();

        var userWord = new UserdataWord(userData, word);
        var createdUserWord = userWordRepository.save(userWord);

        return ConverterDTO.userWordToDto(createdUserWord);
    }

    public List<UserWordDto> listByUserId(Long userId) {
        var userWords = userWordRepository.findAllByUserData_Id(userId);

        var userWordsDtoList = new ArrayList<UserWordDto>();
        for (UserdataWord userWord : userWords) {
            userWordsDtoList.add(ConverterDTO.userWordToDto(userWord));
        }

        return userWordsDtoList;
    }

    public UserWordHasMeaningsDto get(Long userId, Long wordId) {
        var userWordId = new UserdataWordId(userId, wordId);
        var userWord = userWordRepository.findById(userWordId).orElseThrow();

        return ConverterDTO.userWordToDtoWithMeanings(userWord);
    }

    public UserWordHasMeaningsDto getRandomNotLearned(Long userId) {
        var userWord = userWordRepository.findRandomUnlearned(userId);
        if (userWord == null) {
            throw new NoSuchElementException();
        }
        return ConverterDTO.userWordToDtoWithMeanings(userWord);
    }

    public UserWordDto update(Long userId, UserWordUpdationDto userWordDto) {
        var userWordId = new UserdataWordId(userId, userWordDto.getWordId());
        var userWord = userWordRepository.findById(userWordId).orElseThrow();

        userWord.setGuessStreak(userWordDto.getGuessStreak());
        userWord.setLearned(userWordDto.getIsLearned());

        var updatedUserWord = userWordRepository.save(userWord);
        return ConverterDTO.userWordToDto(updatedUserWord);
    }

    public UserWordDto incGuessStreak(Long userId, UserWordIdDto userWordDto) {
        var userWordId = new UserdataWordId(userId, userWordDto.getWordId());
        var userWord = userWordRepository.findById(userWordId).orElseThrow();
        var userData = userDataRepository.findById(userId).orElseThrow();

        userWord.setGuessStreak(userWord.getGuessStreak() + 1);
        if (userWord.getGuessStreak() >= 5) {
            userWord.setLearned(true);
            userData.setPoints(userData.getPoints() + 10);
        }
        userData.setPoints(userData.getPoints() + 1);

        userDataRepository.save(userData);
        var updatedUserWord = userWordRepository.save(userWord);
        return ConverterDTO.userWordToDto(updatedUserWord);
    }

    public UserWordDto setLearned(Long userId, UserWordIdDto userWordDto) {
        var userWordId = new UserdataWordId(userId, userWordDto.getWordId());
        var userWord = userWordRepository.findById(userWordId).orElseThrow();
        var userData = userDataRepository.findById(userId).orElseThrow();

        userWord.setLearned(true);
        userData.setPoints(userData.getPoints() + 10);

        userDataRepository.save(userData);
        var updatedUserWord = userWordRepository.save(userWord);
        return ConverterDTO.userWordToDto(updatedUserWord);
    }

    public void delete(Long userId, Long wordId) {
        var userWordId = new UserdataWordId(userId, wordId);
        if (!userWordRepository.existsById(userWordId)) throw new NoSuchElementException();
        userWordRepository.deleteById(userWordId);
    }
}
