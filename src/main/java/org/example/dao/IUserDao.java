package org.example.dao;

import org.example.entity.User;

import java.util.Set;

public interface IUserDao {
    boolean add(User user);

    User get(int id);

    Set<User> getAll();

    boolean update(User user);

    boolean remove(int id);
}