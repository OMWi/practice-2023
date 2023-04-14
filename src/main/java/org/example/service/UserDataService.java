package org.example.service;

import org.example.model.UserData;
import org.example.repository.UserDataRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserDataService {
    private final UserDataRepository userDataRepository;

    public UserDataService(UserDataRepository userDataRepository) {
        this.userDataRepository = userDataRepository;
    }

    public UserData create(UserData userData) {
        return userDataRepository.save(userData);
    }

    public List<UserData> list() {
        return userDataRepository.findAll();
    }

    public UserData get(Long userDataId) {
        return userDataRepository.findById(userDataId).orElseThrow();
    }

    public UserData update(UserData userData) {
        if (!userDataRepository.existsById(userData.getId())) throw new NoSuchElementException();
        return userDataRepository.save(userData);
    }

    public void delete(Long userDataId) {
        if (!userDataRepository.existsById(userDataId)) throw new NoSuchElementException();
        userDataRepository.deleteById(userDataId);
    }
}
