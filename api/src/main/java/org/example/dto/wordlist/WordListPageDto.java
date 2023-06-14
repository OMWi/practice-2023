package org.example.dto.wordlist;

import java.util.List;

public class WordListPageDto {
    private List<WordListDto> wordLists;
    private int currentPage;
    private long totalItems;
    private int totalPages;

    public WordListPageDto() {
    }

    public List<WordListDto> getWordLists() {
        return wordLists;
    }

    public void setWordLists(List<WordListDto> wordLists) {
        this.wordLists = wordLists;
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
