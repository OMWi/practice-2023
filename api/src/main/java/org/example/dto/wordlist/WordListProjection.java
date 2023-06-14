package org.example.dto.wordlist;

public interface WordListProjection {
    Long getId();
    String getName();
    String getDifficulty();
    Long getPopularity();
    Long getLikes();
    Long getOwnerId();
}
