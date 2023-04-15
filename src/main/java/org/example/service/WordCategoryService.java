package org.example.service;

import org.example.dto.WordCategoryDto;
import org.example.model.WordCategory;
import org.example.repository.WordCategoryRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class WordCategoryService {
    private final WordCategoryRepository wordCategoryRepository;

    public WordCategoryService(WordCategoryRepository wordCategoryRepository) {
        this.wordCategoryRepository = wordCategoryRepository;
    }

    private WordCategoryDto toDto(WordCategory category) {
        var categoryDto = new WordCategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setCategory(category.getCategory());
        return categoryDto;
    }

    public WordCategoryDto create(WordCategoryDto wordCategoryDto) {
        var createdCategory = wordCategoryRepository.save(new WordCategory(wordCategoryDto.getCategory()));
        return toDto(createdCategory);
    }

    public List<WordCategoryDto> list() {
        var categories =  wordCategoryRepository.findAll();

        var categoryDtoList = new ArrayList<WordCategoryDto>();
        for (WordCategory category : categories) {
            var categoryDto = toDto(category);
            categoryDtoList.add(categoryDto);
        }

        return categoryDtoList;
    }

    public WordCategoryDto get(Long wordCategoryId) {
        var category = wordCategoryRepository.findById(wordCategoryId).orElseThrow();
        return toDto(category);
    }

    public WordCategoryDto update(WordCategoryDto wordCategoryDto) {
        var category = wordCategoryRepository.findById(wordCategoryDto.getId()).orElseThrow();
        category.setCategory(wordCategoryDto.getCategory());
        var updatedCategory = wordCategoryRepository.save(category);
        return toDto(updatedCategory);
    }

    public void delete(Long wordCategoryId) {
        if (!wordCategoryRepository.existsById(wordCategoryId)) throw new NoSuchElementException();
        wordCategoryRepository.deleteById(wordCategoryId);
    }
}