package org.example.controller;

import org.example.model.UserCredentials;
import org.example.service.UserCredentialsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @Autowired
    UserCredentialsService userCredentialsService;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserCredentials userCredentials) {
        System.out.println("login request");

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userCredentials.getEmail(), userCredentials.getPassword())
        );


        return new ResponseEntity<>("login success", HttpStatus.OK);
    }

    @GetMapping({"/", "/welcome"})
    public ResponseEntity<?> welcome() {
        System.out.println("welcome request");

        return new ResponseEntity<>("welcome", HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserCredentials userCredentials) {
        System.out.println("register request");

        UserCredentials newUser = userCredentialsService.create(userCredentials);
        if (newUser == null) {
            return new ResponseEntity<>("register failure; user exists", HttpStatus.FORBIDDEN);
        }

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(newUser.getEmail(), newUser.getPassword())
        );

        return new ResponseEntity<>("register success", HttpStatus.OK);
    }

}
