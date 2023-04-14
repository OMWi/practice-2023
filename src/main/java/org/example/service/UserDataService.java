package org.example.service;

import org.example.dto.UserDataDto;
import org.example.dto.WordDto;
import org.example.model.UserData;
import org.example.model.Word;
import org.example.repository.UserCredentialsRepository;
import org.example.repository.UserDataRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserDataService {
    private final UserDataRepository userDataRepository;
    private final UserCredentialsRepository userCredentialsRepository;

    public UserDataService(UserDataRepository userDataRepository, UserCredentialsRepository userCredentialsRepository) {
        this.userDataRepository = userDataRepository;
        this.userCredentialsRepository = userCredentialsRepository;
    }

    public UserData create(UserDataDto userDataDto) {
        var userCredentials = userCredentialsRepository.findById(userDataDto.userId).orElseThrow();

        var userData = new UserData(userCredentials, userDataDto.username);
        return userDataRepository.save(userData);
    }

    public List<UserData> list() {
        return userDataRepository.findAll();
    }

    public UserData get(Long userDataId) {
        return userDataRepository.findById(userDataId).orElseThrow();
    }

    public UserData update(UserDataDto userDataDto) {
        if (!userDataRepository.existsById(userDataDto.userId)) throw new NoSuchElementException();
        var userData = userDataRepository.findById(userDataDto.userId).orElseThrow();
        userData.setUsername(userDataDto.username);
        userData.setPoints(userDataDto.points);

        return userDataRepository.save(userData);
    }

    public void delete(Long userDataId) {
        if (!userDataRepository.existsById(userDataId)) throw new NoSuchElementException();
        userDataRepository.deleteById(userDataId);
    }

//    public Word addWord(Long userId, WordDto wordDto) {
//        if (!userDataRepository.existsById(userId)) throw new NoSuchElementException();
//        var userData = userDataRepository.findById(userId).orElseThrow();
////        userData.
//
//
//    }

}
