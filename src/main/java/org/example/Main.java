package org.example;

import jdk.jfr.Category;
import org.example.controller.AuthenticationController;
import org.example.dao.UserDao;
import org.example.dao.UserDataDao;
import org.example.dao.WordCategoryDao;
import org.example.dao.WordDao;
import org.example.entity.*;
import org.example.enums.UserRole;
import org.example.utils.Utility;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class Main {
    public static User currentUser = null;
    public static UserData currentUserData = null;
    public static boolean isActive = true;

    private static final UserDao userDao = new UserDao();
    private static final UserDataDao userDataDao = new UserDataDao();
    private static final WordDao wordDao = new WordDao();
    private static final WordCategoryDao wordCategoryDao = new WordCategoryDao();


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

    public static void createWordCategory() {
        var scanner = new Scanner(System.in);

        System.out.print("Enter new word category: ");
        String category = scanner.nextLine();
        wordCategoryDao.add(new WordCategory(category));
    }

    public static void wordCategoryMenuAdmin() {
        if (currentUser == null || currentUser.getRole() != UserRole.ADMIN) {
            System.out.println("Access restricted");
            return;
        }

        while (true) {
            int lastOption = 2;
            System.out.println("0. Cancel");
            System.out.println("1. Create word category");
            System.out.println("2. List all categories of words");


            int option = Utility.getOption(0, lastOption);
            if (option == 0) {
                System.out.println();
                return;
            } else if (option == 1) {
                createWordCategory();
                System.out.println();
            } else if (option == 2) {
                var categories = wordCategoryDao.getAll();
                for (WordCategory category : categories) {
                    System.out.println(category);
                }
                System.out.println();
            }
        }
    }

    public static void createWord() {
        var scanner = new Scanner(System.in);

        System.out.print("Enter new word: ");
        String wordText = scanner.nextLine();

        var categories = new ArrayList<>(wordCategoryDao.getAll());
        if (categories.size() == 0) {
            System.out.println("Can not create new word. No word categories found");
            return;
        }
        System.out.println("Select category");
        int index = 1;
        for (WordCategory category : categories) {
            System.out.printf("%d. %s%n", index++, category.getCategory());
        }
        int selectedIndex = Utility.getOption(1, categories.size());
        selectedIndex--;

        wordDao.add(new Word(wordText, categories.get(selectedIndex)));
    }

    public static void wordMenuAdmin() {
        if (currentUser == null || currentUser.getRole() != UserRole.ADMIN) {
            System.out.println("Access restricted");
            return;
        }

        while (true) {
            int lastOption = 2;
            System.out.println("0. Cancel");
            System.out.println("1. Create word");
            System.out.println("2. List all words");


            int option = Utility.getOption(0, lastOption);
            if (option == 0) {
                System.out.println();
                return;
            } else if (option == 1) {
                createWord();
                System.out.println();
            } else if (option == 2) {
                var words = wordDao.getAll();
                for (Word word : words) {
                    System.out.println(word);
                }
                System.out.println();
            }
        }
    }


    public static void addWordToUser() {
        var words = wordDao.getAll();
        var userWords = wordDao.getByUser(currentUser.getId());
//        words.removeAll(userWords);
        var wordsList = new ArrayList<Word>();
        for (Word word : words) {
            boolean isKnown = false;
            for (Word userWord : userWords) {
                if (word.getId() == userWord.getId()) {
                    isKnown = true;
                    break;
                }
            }
            if (!isKnown) {
                wordsList.add(word);
            }
        }
        if (wordsList.size() == 0) {
            System.out.println("Congratulations. You collected all words");
            return;
        }
        int index = 1;
        for (Word word : wordsList) {
            System.out.printf("%d. %s(%s)%n", index++, word.getText(), word.getCategory().getCategory());
        }

        int selectedIndex = Utility.getOption(1, words.size());
        selectedIndex--;

        userDataDao.addWord(currentUser.getId(), wordsList.get(selectedIndex).getId());
    }

    public static void menu() {
        if (currentUser == null) {
            int lastOption = 2;
            System.out.println("Hello, guest");
            System.out.println("0. Close app");
            System.out.println("1. Sign in");
            System.out.println("2. Register");

            int option = Utility.getOption(0, lastOption);
            if (option == 0) {
                System.out.println();
                isActive = false;
            } else if (option == 1) {
                login();
                System.out.println();
            } else if (option == 2) {
                register();
                System.out.println();
            }
            return;
        }
        currentUserData = userDataDao.get(currentUser.getId());

        if (currentUser.getRole() == UserRole.ADMIN) {
            int lastOption = 3;
            System.out.println("Hello, admin");
            System.out.println("0. Close app");
            System.out.println("1. Sign out");
            System.out.println("2. Word crud");
            System.out.println("3. Word category crud");
//            System.out.println(". Meaning crud");
//            System.out.println(". Wordlist crud");
//            System.out.println(". User crud");
//            System.out.println(". Log crud");
//            System.out.println(". tg account crud");
            int option = Utility.getOption(0, lastOption);
            if (option == 0) {
                isActive = false;
            } else if (option == 1) {
                currentUser = null;
            } else if (option == 2) {
                wordMenuAdmin();
            } else if (option == 3) {
                wordCategoryMenuAdmin();
            }
            return;
        }
        if (currentUser.getRole() == UserRole.USER) {
            int lastOption = 3;
            System.out.println("Hello, user '" + currentUserData.getUsername() + "'");
            System.out.println("0. Close app");
            System.out.println("1. Sign out");
            System.out.println("2. Show my words");
            System.out.println("3. Find more words to learn");
//            System.out.println(". Find more lists of words");
//            System.out.println(". Show learned words");
            int option = Utility.getOption(0, lastOption);
            if (option == 0) {
                isActive = false;
            } else if (option == 1) {
                currentUser = null;
            } else if (option == 2) {
                var userWords = wordDao.getByUser(currentUser.getId());
                System.out.println("My words:");
                for (Word word : userWords) {
                    System.out.printf("%s(%s)%n", word.getText(), word.getCategory().getCategory());
                }
                System.out.println();
            } else if (option == 3) {
                addWordToUser();
                System.out.println();
            }
            return;
        }
    }

    public static void main(String[] args) {
        while (isActive) {
            menu();
        }
    }
}