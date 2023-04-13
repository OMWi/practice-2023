package org.example.repository;

import org.example.model.TelegramAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TelegramAccountRepository extends JpaRepository<TelegramAccount, Long> {
}
