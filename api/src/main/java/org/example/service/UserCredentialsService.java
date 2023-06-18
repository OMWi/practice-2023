package org.example.service;

import jakarta.security.auth.message.AuthException;
import org.apache.tomcat.util.http.parser.HttpParser;
import org.example.dto.auth.JwtDto;
import org.example.dto.auth.UserCreationDto;
import org.example.dto.usercredentials.UserCredentialsDto;
import org.example.dto.userdata.UserDataDto;
import org.example.enums.LogType;
import org.example.enums.UserRole;
import org.example.model.Log;
import org.example.model.UserCredentials;
import org.example.model.UserData;
import org.example.repository.LogRepository;
import org.example.repository.UserCredentialsRepository;
import org.example.repository.UserDataRepository;
import org.example.security.JwtUtils;
import org.example.security.UserDetailsImpl;
import org.example.utils.ConverterDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class UserCredentialsService {
    @Autowired
    UserCredentialsRepository userCredentialsRepository;

    @Autowired
    UserDataRepository userDataRepository;

    @Autowired
    LogRepository logRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    PasswordEncoder passwordEncoder;


    public UserDataDto register(UserCreationDto userDto) {
        if (userCredentialsRepository.existsByEmail(userDto.getEmail())) {
            throw new BadCredentialsException("Error: Email is already taken!");
        }

        var userCredentials = new UserCredentials();
        userCredentials.setEmail(userDto.getEmail());
        userCredentials.setPassword(passwordEncoder.encode(userDto.getPassword()));
        var createdUserCredentials = userCredentialsRepository.save(userCredentials);

        var userData = new UserData(createdUserCredentials, userDto.getUsername());
        var createdUserData = userDataRepository.save(userData);

        var log = new Log(userCredentials, LogType.REGISTER);
        logRepository.save(log);

        return ConverterDTO.userDataToDto(createdUserData);
    }

    public JwtDto login(UserCredentialsDto userCredentialsDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userCredentialsDto.getEmail(), userCredentialsDto.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        String role = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .toList().get(0);
        UserRole userRole = UserRole.valueOf(role);

        var userCredentials = userCredentialsRepository.findById(userDetails.getId()).orElseThrow();
        var log = new Log(userCredentials, LogType.LOGIN);
        logRepository.save(log);
        var userData = userDataRepository.findById(userDetails.getId()).orElseThrow();

        return new JwtDto(jwt, userDetails.getId(), userDetails.getEmail(), userRole, userData.getUsername());
    }

    public JwtDto updateCredentials(UserCredentialsDto userCredentialsDto ) {
        var userCredentials = userCredentialsRepository.findById(userCredentialsDto.getId()).orElseThrow();
        if (userCredentialsDto.getEmail() != null && userCredentialsRepository.existsByEmail(userCredentialsDto.getEmail())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (userCredentialsDto.getEmail() != null) {
            userCredentials.setEmail(userCredentialsDto.getEmail());
            var userDetails = (UserDetailsImpl) authentication.getPrincipal();
            userDetails.setEmail(userCredentialsDto.getEmail());
        }
        if (userCredentialsDto.getPassword() != null) {
            userCredentials.setPassword(passwordEncoder.encode(userCredentialsDto.getPassword()));
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authentication.getName(), userCredentialsDto.getPassword())
            );
        }

        userCredentialsRepository.save(userCredentials);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        String role = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .toList().get(0);
        String jwt = jwtUtils.generateJwtToken(authentication);
        return new JwtDto(jwt, userCredentials.getId(), userCredentials.getEmail(), UserRole.valueOf(role), userDetails.getUsername());
    }
}
