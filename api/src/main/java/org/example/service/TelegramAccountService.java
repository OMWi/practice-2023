package org.example.service;

import org.example.dto.telegramaccount.TelegramAccountDto;
import org.example.model.TelegramAccount;
import org.example.repository.TelegramAccountRepository;
import org.example.repository.UserDataRepository;
import org.example.utils.ConverterDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TelegramAccountService {
    private final TelegramAccountRepository telegramAccountRepository;
    private final UserDataRepository userDataRepository;

    public TelegramAccountService(TelegramAccountRepository telegramAccountRepository, UserDataRepository userDataRepository) {
        this.telegramAccountRepository = telegramAccountRepository;
        this.userDataRepository = userDataRepository;
    }

    public TelegramAccountDto create(TelegramAccountDto telegramAccountDto) {
        var userData = userDataRepository.findById(telegramAccountDto.getUserId()).orElseThrow();
        var telegramAccount = new TelegramAccount();
        telegramAccount.setUserData(userData);
        telegramAccount.setChatId(telegramAccountDto.getChatId());
        telegramAccount.setUsername(telegramAccountDto.getUsername());
        telegramAccount.setConfirmed(telegramAccountDto.getIsConfirmed());

        var createdTelegramAccount = telegramAccountRepository.save(telegramAccount);
        return ConverterDTO.telegramAccountToDto(createdTelegramAccount);
    }

    public List<TelegramAccountDto> list() {
        var telegramAccounts = telegramAccountRepository.findAll();

        var telegramAccountDtoList = new ArrayList<TelegramAccountDto>();
        for (TelegramAccount account : telegramAccounts) {
            telegramAccountDtoList.add(ConverterDTO.telegramAccountToDto(account));
        }

        return telegramAccountDtoList;
    }

    public TelegramAccountDto get(Long telegramAccountId) {
        var telegramAccount = telegramAccountRepository.findById(telegramAccountId).orElseThrow();
        return ConverterDTO.telegramAccountToDto(telegramAccount);
    }

    public TelegramAccountDto update(TelegramAccountDto telegramAccountDto) {
        var telegramAccount = telegramAccountRepository.findById(telegramAccountDto.getUserId()).orElseThrow();

        telegramAccount.setChatId(telegramAccountDto.getChatId());
        telegramAccount.setUsername(telegramAccountDto.getUsername());
        telegramAccount.setConfirmed(telegramAccount.isConfirmed());

        var updatedTelegramAccount = telegramAccountRepository.save(telegramAccount);
        return ConverterDTO.telegramAccountToDto(updatedTelegramAccount);
    }

    public void delete(Long telegramAccountId) {
        if (!telegramAccountRepository.existsById(telegramAccountId)) throw new NoSuchElementException();
        telegramAccountRepository.deleteById(telegramAccountId);
    }
}
