package org.example;

import org.example.controller.AuthenticationController;
import org.example.dao.UserDataDao;
import org.example.entity.User;
import org.example.entity.UserData;
import org.example.enums.UserRole;
import org.example.utils.Utility;

import java.util.Scanner;

public class Main {
    public static User currentUser = null;
    public static UserData currentUserData = null;
    public static boolean isActive = true;

    public static void register() {
        if (currentUser != null) {
            System.out.println("Error. First sign out");
            return;
        }

        var scanner = new Scanner(System.in);
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        currentUser = AuthenticationController.register(email, password, username);
    }

    public static void login() {
        if (currentUser != null) {
            System.out.println("Error. First sign out");
            return;
        }

        var scanner = new Scanner(System.in);
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        currentUser = AuthenticationController.login(email, password);
    }

    public static void menu() {
        if (currentUser == null) {
            int optionsNumber = 2;
            System.out.println("Hello, guest");
            System.out.println("0. Close app");
            System.out.println("1. Sign in");
            System.out.println("2. Register");

            int option = Utility.getOption(optionsNumber);
            if (option == 0) {
                isActive = false;
            } else if (option == 1) {
                login();
            } else if (option == 2) {
                register();
            }
            return;
        }
        var userDataDao = new UserDataDao();
        currentUserData = userDataDao.get(currentUser.getId());

        if (currentUser.getRole() == UserRole.ADMIN) {
            int optionsNumber = 1;
            System.out.println("Hello, admin");
            System.out.println("0. Close app");
            System.out.println("1. Sign out");
            System.out.println(". Word crud");
            System.out.println(". Word category crud");
            System.out.println(". Meaning crud");
            System.out.println(". Wordlist crud");
            System.out.println(". User crud");
            System.out.println(". Log crud");
            System.out.println(". tg account crud");
            int option = Utility.getOption(optionsNumber);
            if (option == 0) {
                isActive = false;
            } else if (option == 1) {
                currentUser = null;
            }
            return;
        }
        if (currentUser.getRole() == UserRole.USER) {
            int optionsNumber = 1;
            System.out.println("Hello, user '" + currentUserData.getUsername() + "'");
            System.out.println("0. Close app");
            System.out.println("1. Sign out");
            System.out.println(". Find more words");
            System.out.println(". Find more lists of words");
            System.out.println(". Show my words");
            System.out.println(". Show learned words");
            int option = Utility.getOption(optionsNumber);
            if (option == 0) {
                isActive = false;
            } else if (option == 1) {
                currentUser = null;
            }
            return;
        }
    }

    public static void main(String[] args) {
        while (isActive) {
            menu();
            System.out.println();
        }
    }
}