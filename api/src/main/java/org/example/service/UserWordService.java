package org.example.service;

import org.example.dto.userword.*;
import org.example.model.UserdataWord;
import org.example.model.UserdataWordId;
import org.example.repository.UserDataRepository;
import org.example.repository.UserWordRepository;
import org.example.repository.WordRepository;
import org.example.utils.ConverterDTO;
import org.springframework.boot.web.server.WebServerException;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
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

//    public UserWordDto update(Long userId, UserWordUpdationDto userWordDto) {
//        var userWordId = new UserdataWordId(userId, userWordDto.getWordId());
//        var userWord = userWordRepository.findById(userWordId).orElseThrow();
//
//        userWord.setIsFavorite(userWordDto.getIsFavorite());
//        var updatedUserWord = userWordRepository.save(userWord);
//
//        return ConverterDTO.userWordToDto(updatedUserWord);
//    }

    public void delete(Long userId, Long wordId) {
        var userWordId = new UserdataWordId(userId, wordId);
        if (!userWordRepository.existsById(userWordId)) throw new NoSuchElementException();
        userWordRepository.deleteById(userWordId);
    }

    public UserWordHasMeaningsDto getQuestionWord(Long userId) {
        var today = new Date(System.currentTimeMillis());
        var userWord = userWordRepository.findRandomUnlearned(userId, today);
        if (userWord == null) {
            throw new NoSuchElementException();
        }
        return ConverterDTO.userWordToDtoWithMeanings(userWord);
    }

    public UserWordDto handleQuestionResult(Long userId, QuestionResultDto questionResultDto) {
        var userWordId = new UserdataWordId(userId, questionResultDto.getWordId());
        var userWord = userWordRepository.findById(userWordId).orElseThrow();
        var userData = userDataRepository.findById(userId).orElseThrow();
        var today = new Date(System.currentTimeMillis());

        if (userWord.getIsLearned()) {
            if (questionResultDto.getIsCorrect()) {
                userData.setExp(userData.getExp() + 1);
                userWord.setGuessStreak(userWord.getGuessStreak() + 1);
            } else {
                userWord.setGuessStreak(0);
            }
            userDataRepository.save(userData);
            userWordRepository.save(userWord);
            return ConverterDTO.userWordToDto(userWord);
        }

        if (questionResultDto.getIsCorrect()) {
//            correct answer
            userData.setExp(userData.getExp() + 1);
            if (userWord.getGuessStreak() == 1) {
//                word repeated enough times: increase interval
                switch(userWord.getInterval()) {
                    case 0:
                        userWord.setInterval(1);
                        break;
                    case 1:
                        userWord.setInterval(3);
                        break;
                    case 3:
                        userWord.setInterval(5);
                        break;
                    case 5:
                        userWord.setInterval(10);
                        break;
                    case 10:
                        userWord.setInterval(30);
                        userWord.setIsLearned(true);
                        userData.setExp(userData.getExp() + 5);
                        break;
                }
                userWord.setGuessStreak(0);
                userWord.setIntervalChangeDate(today);
            } else {
                userWord.setGuessStreak(userWord.getGuessStreak() + 1);
            }
        }

        if (!questionResultDto.getIsCorrect()) {
            if (userWord.getGuessStreak() == 0) {
                switch (userWord.getInterval()) {
                    case 10:
                        userWord.setInterval(5);
                        break;
                    case 5:
                        userWord.setInterval(3);
                        break;
                    case 3:
                        userWord.setInterval(1);
                        break;
                }
            } else {
                userWord.setGuessStreak(0);
            }
            userWord.setIntervalChangeDate(today);
        }

        userDataRepository.save(userData);
        userWordRepository.save(userWord);
        return ConverterDTO.userWordToDto(userWord);
    }
}
