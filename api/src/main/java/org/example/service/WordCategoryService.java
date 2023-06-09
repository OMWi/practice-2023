package org.example.service;

import org.example.dto.wordcategory.WordCategoryDto;
import org.example.model.WordCategory;
import org.example.repository.WordCategoryRepository;
import org.example.utils.ConverterDTO;
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

    public WordCategoryDto create(WordCategoryDto wordCategoryDto) {
        var createdCategory = wordCategoryRepository.save(new WordCategory(wordCategoryDto.getCategory()));
        return ConverterDTO.wordCategoryToDto(createdCategory);
    }

    public List<WordCategoryDto> list() {
        var categories =  wordCategoryRepository.findAll();

        var categoryDtoList = new ArrayList<WordCategoryDto>();
        for (WordCategory category : categories) {
            categoryDtoList.add(ConverterDTO.wordCategoryToDto(category));
        }

        return categoryDtoList;
    }

    public WordCategoryDto get(Long wordCategoryId) {
        var category = wordCategoryRepository.findById(wordCategoryId).orElseThrow();
        return ConverterDTO.wordCategoryToDto(category);
    }

    public WordCategoryDto update(WordCategoryDto wordCategoryDto) {
        var category = wordCategoryRepository.findById(wordCategoryDto.getId()).orElseThrow();
        category.setCategory(wordCategoryDto.getCategory());
        var updatedCategory = wordCategoryRepository.save(category);
        return ConverterDTO.wordCategoryToDto(updatedCategory);
    }

    public void delete(Long wordCategoryId) {
        if (!wordCategoryRepository.existsById(wordCategoryId)) throw new NoSuchElementException();
        wordCategoryRepository.deleteById(wordCategoryId);
    }
}