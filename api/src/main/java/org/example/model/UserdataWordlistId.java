package org.example.model;

import jakarta.persistence.Column;

import java.io.Serializable;
import java.util.Objects;

public class UserdataWordlistId implements Serializable {
    @Column(name = "userdata_id")
    private Long userdataId;

    @Column(name = "wordlist_id")
    private Long wordlistId;

    public UserdataWordlistId() {
    }

    public UserdataWordlistId(long userdataId, long wordlistId) {
        this.userdataId = userdataId;
        this.wordlistId = wordlistId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserdataWordlistId that = (UserdataWordlistId) o;
        return Objects.equals(userdataId, that.userdataId) && Objects.equals(wordlistId, that.wordlistId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userdataId, wordlistId);
    }

    public Long getUserdataId() {
        return userdataId;
    }

    public void setUserdataId(Long userdataId) {
        this.userdataId = userdataId;
    }

    public Long getWordlistId() {
        return wordlistId;
    }

    public void setWordlistId(Long wordlistId) {
        this.wordlistId = wordlistId;
    }
}
