package org.example.dao;

import org.example.entity.UserData;

import java.util.Set;

public interface IUserDataDao {
    boolean add(UserData userData);

    UserData get(int userId);

    Set<UserData> getAll();

    boolean update(int userId, UserData userData);
}
