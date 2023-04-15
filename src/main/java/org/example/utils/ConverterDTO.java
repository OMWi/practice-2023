package org.example.utils;

import org.example.dto.MeaningDto;
import org.example.dto.WordCategoryDto;
import org.example.dto.WordDto;
import org.example.model.Meaning;
import org.example.model.Word;
import org.example.model.WordCategory;

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
}
