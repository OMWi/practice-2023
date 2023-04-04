package org.example.datalayer;

import org.example.models.User;

import java.util.List;

public interface IUserDao {
    User getUser(int id);

    List<User> getAllUsers();

    boolean insertUser(User user);

    boolean updateUser(User user);

    boolean deleteUser(int id);
}
