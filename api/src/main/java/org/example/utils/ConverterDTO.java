package org.example.utils;

import org.example.dto.difficulty.DifficultyDto;
import org.example.dto.meaning.MeaningDto;
import org.example.dto.userdata.UserDataDto;
import org.example.dto.userword.UserWordDto;
import org.example.dto.userword.UserWordHasMeaningsDto;
import org.example.dto.word.WordHasMeaningsDto;
import org.example.dto.wordcategory.WordCategoryDto;
import org.example.dto.word.WordDto;
import org.example.dto.wordlist.WordListDto;
import org.example.dto.wordlist.WordListHasWordsDto;
import org.example.model.*;

import java.sql.Date;
import java.util.ArrayList;

public final class ConverterDTO {
    private ConverterDTO() {
    }

    public static WordCategoryDto wordCategoryToDto(WordCategory category) {
        var categoryDto = new WordCategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setCategory(category.getCategory());
        return categoryDto;
    }

    public static DifficultyDto difficultyToDto(Difficulty difficulty) {
        var difficultyDto = new DifficultyDto();
        difficultyDto.setId(difficulty.getId());
        difficultyDto.setDifficulty(difficulty.getDifficulty());
        return difficultyDto;
    }

    public static MeaningDto meaningToDto(Meaning meaning) {
        var meaningDto = new MeaningDto();
        meaningDto.setId(meaning.getId());
        meaningDto.setDifficulty(meaning.getDifficulty().getDifficulty());
        meaningDto.setMeaning(meaning.getMeaning());
        meaningDto.setWordId(meaning.getWord().getId());
        return meaningDto;
    }

    public static WordDto wordToDto(Word word) {
        var wordDto = new WordDto();
        wordDto.setId(word.getId());
        wordDto.setWord(word.getWord());
        wordDto.setDifficulty(word.getDifficulty().getDifficulty());
        wordDto.setCategoryDto(wordCategoryToDto(word.getCategory()));

        return wordDto;
    }

    public static WordHasMeaningsDto wordToDtoWithMeanings(Word word) {
        var wordDto = new WordHasMeaningsDto();
        wordDto.setId(word.getId());
        wordDto.setWord(word.getWord());
        wordDto.setDifficulty(word.getDifficulty().getDifficulty());
        wordDto.setCategoryDto(wordCategoryToDto(word.getCategory()));

        var meaningDtoList = new ArrayList<MeaningDto>();
        var wordMeanings = word.getMeanings();
        for (Meaning meaning : wordMeanings) {
            meaningDtoList.add(meaningToDto(meaning));
        }
        wordDto.setMeaningDtoList(meaningDtoList);

        return wordDto;
    }

    public static WordListDto wordListToDto(WordList wordList) {
        var wordListDto = new WordListDto();
        wordListDto.setId(wordList.getId());
        wordListDto.setName(wordList.getName());
        wordListDto.setLikes(wordList.getLikes());
        wordListDto.setPopularity(wordList.getPopularity());
        wordListDto.setDifficulty(wordList.getDifficulty().getDifficulty());
        return wordListDto;
    }

    public static WordListHasWordsDto wordListToDtoWithWords(WordList wordList) {
        var wordListDto = new WordListHasWordsDto();
        wordListDto.setId(wordList.getId());
        wordListDto.setName(wordList.getName());
        wordListDto.setLikes(wordList.getLikes());
        wordListDto.setPopularity(wordList.getPopularity());
        wordListDto.setDifficulty(wordList.getDifficulty().getDifficulty());

        var wordDtoList = new ArrayList<WordDto>();
        var wordsOfWordList = wordList.getWords();
        for (Word word : wordsOfWordList) {
            wordDtoList.add(wordToDto(word));
        }
        wordListDto.setWordDtoList(wordDtoList);

        return wordListDto;
    }

    public static UserDataDto userDataToDto(UserData userData) {
        var userDataDto = new UserDataDto();
        userDataDto.setUserId(userData.getId());
        userDataDto.setUsername(userData.getUsername());
        userDataDto.setExp(userData.getExp());
        var today = new Date(System.currentTimeMillis());
        if (userData.getSubscriptionExpirationDate() != null && today.compareTo(userData.getSubscriptionExpirationDate()) > 0 ) {
            userDataDto.setIsSubscriber(true);
        }
        userDataDto.setIsSubscriber(false);

        return userDataDto;
    }

    public static UserWordDto userWordToDto(UserdataWord userWord) {
        var userWordDto = new UserWordDto();
        userWordDto.setUserId(userWord.getUserData().getId());
        userWordDto.setWordId(userWord.getWord().getId());
        userWordDto.setDifficulty(userWord.getWord().getDifficulty().getDifficulty());
        userWordDto.setIsLearned(userWord.isLearned());
        userWordDto.setGuessStreak(userWord.getGuessStreak());
        userWordDto.setInterval(userWord.getInterval());
        userWordDto.setIntervalChangeDate(userWord.getIntervalChangeDate());
        userWordDto.setWord(userWord.getWord().getWord());
        userWordDto.setCategory(userWord.getWord().getCategory().getCategory());
        return userWordDto;
    }

    public static UserWordHasMeaningsDto userWordToDtoWithMeanings(UserdataWord userWord) {
        var userWordDto = new UserWordHasMeaningsDto();
        userWordDto.setDifficulty(userWord.getWord().getDifficulty().getDifficulty());
        userWordDto.setUserId(userWord.getUserData().getId());
        userWordDto.setWordId(userWord.getWord().getId());
        userWordDto.setIsLearned(userWord.isLearned());
        userWordDto.setGuessStreak(userWord.getGuessStreak());
        userWordDto.setInterval(userWord.getInterval());
        userWordDto.setIntervalChangeDate(userWord.getIntervalChangeDate());
        userWordDto.setWord(userWord.getWord().getWord());
        userWordDto.setCategory(userWord.getWord().getCategory().getCategory());

        var meaningDtoList = new ArrayList<MeaningDto>();
        var wordMeanings = userWord.getWord().getMeanings();
        for (Meaning meaning : wordMeanings) {
            meaningDtoList.add(meaningToDto(meaning));
        }
        userWordDto.setMeaningDtoList(meaningDtoList);
        return userWordDto;
    }
}
