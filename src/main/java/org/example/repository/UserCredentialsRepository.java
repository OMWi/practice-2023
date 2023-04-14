package org.example.repository;

import org.example.model.UserCredentials;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCredentialsRepository extends JpaRepository<UserCredentials, Long> {
    UserCredentials findByEmail(String email);

    boolean existsByEmail(String email);
}
