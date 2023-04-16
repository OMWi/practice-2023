package org.example.controller;

import org.example.dto.auth.JwtDto;
import org.example.dto.auth.UserCreationDto;
import org.example.dto.usercredentials.UserCredentialsDto;
import org.example.dto.userdata.UserDataDto;
import org.example.security.JwtUtils;
import org.example.service.UserCredentialsService;
import org.example.service.UserDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @Autowired
    UserCredentialsService userCredentialsService;

    @Autowired
    UserDataService userDataService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/register")
    public UserDataDto registerUser(@RequestBody UserCreationDto userDto) {
        try {
            return userCredentialsService.register(userDto);
        } catch (BadCredentialsException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/login")
    public JwtDto login(@RequestBody UserCredentialsDto userCredentialsDto) {
        return userCredentialsService.login(userCredentialsDto);
    }

}
