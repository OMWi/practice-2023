package org.example.service;

import org.example.model.Log;
import org.example.repository.LogRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class LogService {
    private final LogRepository logRepository;

    public LogService(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    public Log create(Log log) {
        return logRepository.save(log);
    }

    public List<Log> list() {
        return logRepository.findAll();
    }

    public Log get(Long logId) {
        return logRepository.findById(logId).orElseThrow();
    }

    public Log update(Log log) {
        if (!logRepository.existsById(log.getId())) throw new NoSuchElementException();
        return logRepository.save(log);
    }

    public void delete(Long logId) {
        if (!logRepository.existsById(logId)) throw new NoSuchElementException();
        logRepository.deleteById(logId);
    }
}
