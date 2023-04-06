package org.example.dao;

import org.example.entity.TelegramAccount;

import java.util.Set;

public interface ITelegramAccountDao {
    boolean add(TelegramAccount telegramAccount);

    TelegramAccount get(int userId);

    Set<TelegramAccount> getAll();

    boolean update(int userId, TelegramAccount telegramAccount);

    boolean remove(int userId);
}
