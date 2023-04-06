package org.example.dao;

import org.example.entity.Log;

import java.util.Set;

public interface ILogDao {
    boolean add(Log log);

    Log get(int id);

    Set<Log> getAll();

    boolean remove(int id);
}
