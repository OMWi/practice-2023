package org.example.controller;

import org.example.dto.telegramaccount.TelegramAccountDto;
import org.example.service.TelegramAccountService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/v1/telegram-accounts")
public class TelegramAccountController {
    private final TelegramAccountService telegramAccountService;

    public TelegramAccountController(TelegramAccountService telegramAccountService) {
        this.telegramAccountService = telegramAccountService;
    }

    @PostMapping
    @Secured({"ADMIN", "USER"})
    @ResponseStatus(HttpStatus.CREATED)
    public TelegramAccountDto createTelegramAccount(@RequestBody TelegramAccountDto telegramAccountDto) {
        return telegramAccountService.create(telegramAccountDto);
    }

    @GetMapping
    @Secured({"ADMIN"})
    public List<TelegramAccountDto> listTelegramAccounts() {
        return telegramAccountService.list();
    }

    @GetMapping("/{telegramAccountId}")
    @Secured({"ADMIN", "USER"})
    public TelegramAccountDto getTelegramAccount(@PathVariable("telegramAccountId") Long telegramAccountId) {
        try {
            return telegramAccountService.get(telegramAccountId);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping
    @Secured({"ADMIN", "USER"})
    public TelegramAccountDto updateTelegramAccount(@RequestBody TelegramAccountDto telegramAccountDto) {
        try {
            return telegramAccountService.update(telegramAccountDto);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{telegramAccountId}")
    @Secured({"ADMIN", "USER"})
    public void deleteTelegramAccount(@PathVariable("telegramAccountId") Long telegramAccountId) {
        try {
            telegramAccountService.delete(telegramAccountId);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
