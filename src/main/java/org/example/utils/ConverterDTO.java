package org.example.utils;

import org.example.dto.meaning.MeaningDto;
import org.example.dto.telegramaccount.TelegramAccountDto;
import org.example.dto.userdata.UserDataDto;
import org.example.dto.wordcategory.WordCategoryDto;
import org.example.dto.word.WordDto;
import org.example.dto.wordlist.WordListDto;
import org.example.dto.wordlist.WordListHasWordsDto;
import org.example.model.*;

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

    public static MeaningDto meaningToDto(Meaning meaning) {
        var meaningDto = new MeaningDto();
        meaningDto.setId(meaning.getId());
        meaningDto.setLevel(meaning.getLevel());
        meaningDto.setText(meaning.getText());
        meaningDto.setWordId(meaning.getWord().getId());
        return meaningDto;
    }

    public static WordDto wordToDto(Word word) {
        var wordDto = new WordDto();
        wordDto.setId(word.getId());
        wordDto.setText(word.getText());
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
        wordListDto.setPopularity(wordList.getPopularity());
        return wordListDto;
    }

    public static WordListHasWordsDto wordListToDtoWithWords(WordList wordList) {
        var wordListDto = new WordListHasWordsDto();
        wordListDto.setId(wordList.getId());
        wordListDto.setName(wordList.getName());
        wordListDto.setPopularity(wordList.getPopularity());

        var wordDtoList = new ArrayList<WordDto>();
        var wordsOfWordList = wordList.getWords();
        for (Word word : wordsOfWordList) {
            wordDtoList.add(wordToDto(word));
        }
        wordListDto.setWordDtoList(wordDtoList);

        return wordListDto;
    }

    public static TelegramAccountDto telegramAccountToDto(TelegramAccount telegramAccount) {
        var telegramAccountDto = new TelegramAccountDto();
        telegramAccountDto.setUserId(telegramAccount.getId());
        telegramAccountDto.setChatId(telegramAccount.getChatId());
        telegramAccountDto.setUsername(telegramAccount.getUsername());
        telegramAccountDto.setIsConfirmed(telegramAccount.isConfirmed());
        return telegramAccountDto;
    }

    public static UserDataDto userDataToDto(UserData userData) {
        var userDataDto = new UserDataDto();
        userDataDto.setUserId(userData.getId());
        userDataDto.setUsername(userData.getUsername());
        userDataDto.setPoints(userData.getPoints());
        userDataDto.setTelegramAccountDto(telegramAccountToDto(userData.getTelegramAccount()));
        return userDataDto;
    }
}
