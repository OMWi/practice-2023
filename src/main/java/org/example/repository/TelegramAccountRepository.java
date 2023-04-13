package org.example.repository;

import org.example.model.TelegramAccount;
import org.springframework.data.repository.CrudRepository;

public interface TelegramAccountRepository extends CrudRepository<TelegramAccount, Long> {
}
