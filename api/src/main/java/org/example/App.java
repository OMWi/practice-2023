package org.example;

import org.example.enums.UserRole;
import org.example.model.UserCredentials;
import org.example.repository.UserCredentialsRepository;
import org.example.service.UserCredentialsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Autowired
    PasswordEncoder passwordEncoder;

//    @Autowired
//    private UserCredentialsService userCredentialsService;
//
//    @Override
//    public void run(String... args) throws Exception {
//
//    }
}