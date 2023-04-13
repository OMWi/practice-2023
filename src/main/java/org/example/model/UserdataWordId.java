package org.example.model;

import jakarta.persistence.Column;

import java.io.Serializable;
import java.util.Objects;

public class UserdataWordId implements Serializable {
    @Column(name = "userdata_id")
    private Long userdataId;

    @Column(name = "word_id")
    private Long wordId;

    public UserdataWordId() {
    }

    public UserdataWordId(long userdataId, long wordId) {
        this.userdataId = userdataId;
        this.wordId = wordId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserdataWordId that = (UserdataWordId) o;
        return Objects.equals(userdataId, that.userdataId) && Objects.equals(wordId, that.wordId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userdataId, wordId);
    }

    public Long getUserdataId() {
        return userdataId;
    }

    public void setUserdataId(Long userdataId) {
        this.userdataId = userdataId;
    }

    public Long getWordId() {
        return wordId;
    }

    public void setWordId(Long wordId) {
        this.wordId = wordId;
    }
}
