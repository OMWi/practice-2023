package org.example.utils;

import org.example.dto.difficulty.DifficultyDto;
import org.example.dto.meaning.MeaningDto;
import org.example.dto.userdata.UserDataDto;
import org.example.dto.userword.UserWordDto;
import org.example.dto.userword.UserWordHasMeaningsDto;
import org.example.dto.word.WordHasMeaningsDto;
import org.example.dto.wordcategory.WordCategoryDto;
import org.example.dto.word.WordDto;
import org.example.dto.userwordlist.UserWordListDto;
import org.example.dto.wordlist.WordListDto;
import org.example.dto.wordlist.WordListHasWordsDto;
import org.example.model.*;

import java.util.ArrayList;

public final class ConverterDTO {
    private ConverterDTO() {
    }

    public static WordCategoryDto wordCategoryToDto(WordCategory category) {
        var categoryDto = new WordCategoryDto(
                category.getId(),
                category.getCategory()
        );
        return categoryDto;
    }

    public static DifficultyDto difficultyToDto(Difficulty difficulty) {
        var difficultyDto = new DifficultyDto(
                difficulty.getId(),
                difficulty.getDifficulty()
        );
        return difficultyDto;
    }

    public static MeaningDto meaningToDto(Meaning meaning) {
        var meaningDto = new MeaningDto(
                meaning.getId(),
                meaning.getDifficulty().getDifficulty(),
                meaning.getMeaning(),
                meaning.getWord().getId()
        );
        return meaningDto;
    }

    public static WordDto wordToDto(Word word) {
        var wordDto = new WordDto(
                word.getId(),
                word.getWord(),
                wordCategoryToDto(word.getCategory()),
                word.getDifficulty().getDifficulty()
        );
        return wordDto;
    }

    public static WordHasMeaningsDto wordToDtoWithMeanings(Word word) {
        var meaningDtoList = new ArrayList<MeaningDto>();
        var wordMeanings = word.getMeanings();
        for (Meaning meaning : wordMeanings) {
            meaningDtoList.add(meaningToDto(meaning));
        }

        var wordDto = new WordHasMeaningsDto(
                word.getId(),
                word.getWord(),
                wordCategoryToDto(word.getCategory()),
                meaningDtoList,
                word.getDifficulty().getDifficulty()
        );
        return wordDto;
    }

    public static WordListDto wordListToDto(WordList wordList, Long likes, Long popularity) {
        var wordListDto = new WordListDto(
                wordList.getId(),
                wordList.getName(),
                wordList.getDifficulty().getDifficulty(),
                likes,
                popularity,
                userDataToDto(wordList.getOwner())
        );
        return wordListDto;
    }

    public static WordListHasWordsDto wordListToDtoWithWords(WordList wordList, Long likes, Long popularity) {
        var wordDtoList = new ArrayList<WordDto>();
        var wordsOfWordList = wordList.getWords();
        for (Word word : wordsOfWordList) {
            wordDtoList.add(wordToDto(word));
        }

        var wordListDto = new WordListHasWordsDto(
                wordList.getId(),
                wordList.getName(),
                wordList.getDifficulty().getDifficulty(),
                likes,
                popularity,
                userDataToDto(wordList.getOwner()),
                wordDtoList
        );
        return wordListDto;
    }

    public static UserDataDto userDataToDto(UserData userData) {
        var userDataDto = new UserDataDto(
                userData.getId(),
                userData.getUsername(),
                userData.getExp(),
                Utility.isSubscriber(userData),
                userData.getSubscriptionExpirationDate()
        );
        return userDataDto;
    }

    public static UserWordDto userWordToDto(UserdataWord userWord) {
        var userWordDto = new UserWordDto(
                userWord.getUserData().getId(),
                userWord.getWord().getId(),
                userWord.getIsLearned(),
                userWord.getGuessStreak(),
                userWord.getWord().getWord(),
                userWord.getWord().getCategory().getCategory(),
                userWord.getWord().getDifficulty().getDifficulty(),
                userWord.getIntervalChangeDate(),
                userWord.getInterval()
        );
        return userWordDto;
    }

    public static UserWordHasMeaningsDto userWordToDtoWithMeanings(UserdataWord userWord) {
        var meaningDtoList = new ArrayList<MeaningDto>();
        var wordMeanings = userWord.getWord().getMeanings();
        for (Meaning meaning : wordMeanings) {
            meaningDtoList.add(meaningToDto(meaning));
        }

        var userWordDto = new UserWordHasMeaningsDto(
                userWord.getUserData().getId(),
                userWord.getWord().getId(),
                userWord.getIsLearned(),
                userWord.getGuessStreak(),
                userWord.getWord().getWord(),
                userWord.getWord().getDifficulty().getDifficulty(),
                userWord.getIntervalChangeDate(),
                userWord.getInterval(),
                userWord.getWord().getCategory().getCategory(),
                meaningDtoList
        );
        return userWordDto;
    }

    public static UserWordListDto userWordListToDto(UserdataWordlist userWordList) {
        var userWordListDto = new UserWordListDto(
                userWordList.getUserData().getId(),
                userWordList.getWordList().getId(),
                userWordList.getIsFavorite(),
                userWordList.getWordList().getDifficulty().getDifficulty(),
                userWordList.getWordList().getName(),
                userDataToDto(userWordList.getWordList().getOwner())
        );
        return userWordListDto;
    }
}
