package org.example.service;

import org.example.dto.userdata.UserDataCreationDto;
import org.example.dto.userdata.UserDataDto;
import org.example.dto.userdata.UserDataUpdationDto;
import org.example.model.UserData;
import org.example.repository.TelegramAccountRepository;
import org.example.repository.UserCredentialsRepository;
import org.example.repository.UserDataRepository;
import org.example.utils.ConverterDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserDataService {
    private final UserDataRepository userDataRepository;
    private final UserCredentialsRepository userCredentialsRepository;
    private final TelegramAccountRepository telegramAccountRepository;

    public UserDataService(UserDataRepository userDataRepository, UserCredentialsRepository userCredentialsRepository, TelegramAccountRepository telegramAccountRepository) {
        this.userDataRepository = userDataRepository;
        this.userCredentialsRepository = userCredentialsRepository;
        this.telegramAccountRepository = telegramAccountRepository;
    }

    public UserDataDto create(UserDataCreationDto userDataDto) {
        var userCredentials = userCredentialsRepository.findById(userDataDto.getUserId()).orElseThrow();
        var userData = new UserData(userCredentials, userDataDto.getUsername());
        var createdUserData = userDataRepository.save(userData);
        return ConverterDTO.userDataToDto(createdUserData);
    }

    public List<UserDataDto> list() {
        var usersData = userDataRepository.findAll();

        var userDataDtoList = new ArrayList<UserDataDto>();
        for (UserData userData : usersData) {
            userDataDtoList.add(ConverterDTO.userDataToDto(userData));
        }

        return userDataDtoList;
    }

    public UserDataDto get(Long userDataId) {
        var userData = userDataRepository.findById(userDataId).orElseThrow();
        return ConverterDTO.userDataToDto(userData);
    }

    public UserDataDto update(UserDataUpdationDto userDataDto) {
        var userData = userDataRepository.findById(userDataDto.getUserId()).orElseThrow();

        userData.setUsername(userDataDto.getUsername());
        userData.setPoints(userDataDto.getPoints());
        var telegramAccount = telegramAccountRepository.findById(userDataDto.getTelegramAccountId()).orElseThrow();
        userData.setTelegramAccount(telegramAccount);

        var updatedUserData = userDataRepository.save(userData);
        return ConverterDTO.userDataToDto(updatedUserData);
    }

    public void delete(Long userDataId) {
        if (!userDataRepository.existsById(userDataId)) throw new NoSuchElementException();
        userDataRepository.deleteById(userDataId);
    }
}
