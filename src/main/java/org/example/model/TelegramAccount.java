package org.example.model;


import jakarta.persistence.*;
import org.hibernate.annotations.NaturalId;

@Entity
@Table(name = "telegram_account")
public class TelegramAccount {
    @Id
    @Column(name = "user_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId("user_id")
    @JoinColumn(name = "user_id")
    private UserData userData;

    @NaturalId
    private long chatId;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private boolean isConfirmed = false;

    public TelegramAccount() {
    }

    public TelegramAccount(long chatId, String username) {
        this.chatId = chatId;
        this.username = username;
    }

    @Override
    public String toString() {
        return "TelegramAccount{" +
                "id=" + id +
                ", chatId=" + chatId +
                ", username='" + username + '\'' +
                ", isConfirmed=" + isConfirmed +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserData getUserData() {
        return userData;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }

    public long getChatId() {
        return chatId;
    }

    public void setChatId(long chatId) {
        this.chatId = chatId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isConfirmed() {
        return isConfirmed;
    }

    public void setConfirmed(boolean confirmed) {
        isConfirmed = confirmed;
    }
}
