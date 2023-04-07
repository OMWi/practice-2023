package org.example.controller;

import org.example.dao.UserDao;
import org.example.dao.UserDataDao;
import org.example.entity.User;
import org.example.entity.UserData;

public final class AuthenticationController {
    private AuthenticationController() {
    }

    private static String hash(String text) {
        return text + "hashed";
    }

    private static String salt(String text) {
        return "salted" + text;
    }

    public static User register(String email, String password, String username) {
        var user = new User(
                email,
                hash(salt(password)),
                "salted"
        );
        var userDao = new UserDao();
        boolean isUserInserted = userDao.add(user);
        user = userDao.getByEmail(email);
        if (user == null) {
            return user;
        }

        var userDataDao = new UserDataDao();
        var userData = new UserData(
                user.getId(),
                username
        );
        boolean isUserDataInserted = userDataDao.add(userData);

        return user;
    }

    public static User login(String email, String password) {
        var userDao = new UserDao();
        var user = userDao.getByEmail(email);
        if (user == null) {
            System.out.println("User not found");
            return null;
        }

        if (!user.getHashed().equals(hash(user.getSalt() + password))) {
            System.out.println("Incorrect credentials");
            return null;
        }

        return user;
    }


}
