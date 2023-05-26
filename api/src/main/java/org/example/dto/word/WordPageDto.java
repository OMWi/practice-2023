package org.example.dto.word;

import java.util.List;

public class WordPageDto {
    private List<WordDto> words;
    private int currentPage;
    private long totalItems;
    private int totalPages;

    public WordPageDto() {
    }

    public List<WordDto> getWords() {
        return words;
    }

    public void setWords(List<WordDto> words) {
        this.words = words;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public long getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(long totalItems) {
        this.totalItems = totalItems;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}
