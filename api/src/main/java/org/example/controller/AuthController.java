package org.example.controller;

import org.example.dto.auth.JwtDto;
import org.example.dto.auth.UserCreationDto;
import org.example.dto.usercredentials.UserCredentialsDto;
import org.example.dto.userdata.UserDataDto;
import org.example.enums.UserRole;
import org.example.security.JwtUtils;
import org.example.security.UserDetailsImpl;
import org.example.service.UserCredentialsService;
import org.example.service.UserDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.NoSuchElementException;

@CrossOrigin
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

    @PutMapping
    @Secured({"ADMIN", "USER"})
    public JwtDto updateCredentials(@RequestBody UserCredentialsDto userCredentialsDto) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            String role = userDetails.getAuthorities().stream()
                    .map(item -> item.getAuthority())
                    .toList().get(0);
            if (UserRole.valueOf(role) != UserRole.ADMIN && userCredentialsDto.getId() != userDetails.getId()) {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN);
            }
            return userCredentialsService.updateCredentials(userCredentialsDto);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

}
