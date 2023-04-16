package org.example;

import org.example.enums.UserRole;
import org.example.model.UserCredentials;
import org.example.repository.UserCredentialsRepository;
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
//    private BCryptPasswordEncoder passwordEncoder;
//
//    @Autowired
//    private UserCredentialsRepository userCredentialsRepository;
//
//    @Override
//    public void run(String... args) throws Exception {
////        userCredentialsRepository.save(new UserCredentials(
////                "user@gmail.com",
////                passwordEncoder.encode("pass"),
////                UserRole.USER
////                ));
//    }
}