package org.example.service;

import org.example.dto.userdata.UserDataCreationDto;
import org.example.dto.userdata.UserDataDto;
import org.example.dto.userdata.UserDataHaveRowNumberDto;
import org.example.dto.userdata.UserDataUpdationDto;
import org.example.dto.wordlist.WordListDto;
import org.example.enums.UserRole;
import org.example.model.UserData;
import org.example.repository.UserCredentialsRepository;
import org.example.repository.UserDataRepository;
import org.example.repository.WordListRepository;
import org.example.security.UserDetailsImpl;
import org.example.utils.ConverterDTO;
import org.example.utils.Utility;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserDataService {
    private final UserDataRepository userDataRepository;
    private final UserCredentialsRepository userCredentialsRepository;
    private final WordListRepository wordListRepository;

    public UserDataService(UserDataRepository userDataRepository, UserCredentialsRepository userCredentialsRepository, WordListRepository wordListRepository) {
        this.userDataRepository = userDataRepository;
        this.userCredentialsRepository = userCredentialsRepository;
        this.wordListRepository = wordListRepository;
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

    public List<UserDataHaveRowNumberDto> getLeaderboard() {
        var usersData = userDataRepository.findTop100ByOrderByExpDesc();
        var userDataDtoList = new ArrayList<UserDataHaveRowNumberDto>();
        for (UserData userData : usersData) {
            var userDataDto = new UserDataHaveRowNumberDto();
            userDataDto.setUserId(userData.getId());
            userDataDto.setUsername(userData.getUsername());
            userDataDto.setExp(userData.getExp());
            long position = usersData
                    .stream()
                    .filter(item -> item.getExp() > userData.getExp())
                    .count()
                    + 1;
            userDataDto.setPosition(position);
            userDataDtoList.add(userDataDto);
        }

        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() == "anonymousUser" || !authentication.isAuthenticated()) {
            return userDataDtoList;
        }

        var userId = ((UserDetailsImpl) authentication.getPrincipal()).getId();
        if (userDataDtoList.stream().anyMatch(userDataDto -> userDataDto.getUserId().equals(userId))) {
            return userDataDtoList;
        }

        var currentUserData = userDataRepository.findById(userId).orElseThrow();
        long currentUserPosition = userDataRepository.countByExpGreaterThan(currentUserData.getExp()) + 1;

        var userDataDto = new UserDataHaveRowNumberDto();
        userDataDto.setUserId(userId);
        userDataDto.setUsername(currentUserData.getUsername());
        userDataDto.setExp(currentUserData.getExp());
        userDataDto.setPosition(currentUserPosition);
        userDataDtoList.add(userDataDto);

        return userDataDtoList;
    }

    public UserDataDto get(Long userDataId) {
        var userData = userDataRepository.findById(userDataId).orElseThrow();
        return ConverterDTO.userDataToDto(userData);
    }

    public boolean isSubscriber(Long userId) {
        var userData = userDataRepository.findById(userId).orElseThrow();
        return Utility.isSubscriber(userData);
    }

    public UserDataDto update(UserDataUpdationDto userDataDto) {
        var userData = userDataRepository.findById(userDataDto.getUserId()).orElseThrow();

        if (userDataDto.getUsername() != null) {
            userData.setUsername(userDataDto.getUsername());
        }

        var updatedUserData = userDataRepository.save(userData);
        return ConverterDTO.userDataToDto(updatedUserData);
    }

    public void delete(Long userDataId) {
        if (!userDataRepository.existsById(userDataId)) throw new NoSuchElementException();
        userDataRepository.deleteById(userDataId);
    }

//    public void addWordList(Long userId, Long wordListId) {
//        var userData = userDataRepository.findById(userId).orElseThrow();
//        var wordList = wordListRepository.findById(wordListId).orElseThrow();
//        userData.addWordList(wordList);
//        userDataRepository.save(userData);
//        wordList.setPopularity(wordList.getPopularity()+1);
//        wordListRepository.save(wordList);
//    }
//
//    public WordListDto getWordList(Long userId, Long wordListId) {
//        var userData = userDataRepository.findById(userId).orElseThrow();
//        var filteredWordList = userData.getWordLists().stream().filter(wordList -> wordList.getId().equals(wordListId)).findFirst().orElseThrow();
//        return ConverterDTO.wordListToDto(filteredWordList);
//    }
//
//    public void deleteWordList(Long userId, Long wordListId) {
//        var userData = userDataRepository.findById(userId).orElseThrow();
//        var wordList = wordListRepository.findById(wordListId).orElseThrow();
//        userData.removeWordList(wordList);
//        userDataRepository.save(userData);
//    }
}
