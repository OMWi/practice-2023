package org.example.datalayer;

import org.example.models.UserData;

import java.util.Set;

public interface IUserDataDao {
    UserData getUserData(int id);

    Set<UserData> getAllSetsOfUserData();

    boolean insertUserData(UserData userData);

    boolean updateUserData(UserData userData);

    boolean deleteUserData(int id);
}
