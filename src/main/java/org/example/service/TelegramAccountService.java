package org.example.service;

import org.example.model.TelegramAccount;
import org.example.repository.TelegramAccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TelegramAccountService {
    private final TelegramAccountRepository telegramAccountRepository;

    public TelegramAccountService(TelegramAccountRepository telegramAccountRepository) {
        this.telegramAccountRepository = telegramAccountRepository;
    }

    public TelegramAccount create(TelegramAccount telegramAccount) {
        return telegramAccountRepository.save(telegramAccount);
    }

    public List<TelegramAccount> list() {
        return telegramAccountRepository.findAll();
    }

    public TelegramAccount get(Long telegramAccountId) {
        return telegramAccountRepository.findById(telegramAccountId).orElseThrow();
    }

    public TelegramAccount update(TelegramAccount telegramAccount) {
        if (!telegramAccountRepository.existsById(telegramAccount.getId())) throw new NoSuchElementException();
        return telegramAccountRepository.save(telegramAccount);
    }

    public void delete(Long telegramAccountId) {
        if (!telegramAccountRepository.existsById(telegramAccountId)) throw new NoSuchElementException();
        telegramAccountRepository.deleteById(telegramAccountId);
    }
}
