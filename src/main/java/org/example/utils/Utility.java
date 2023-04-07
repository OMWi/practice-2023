package org.example.utils;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public final class Utility {
    private static final System.Logger logger = System.getLogger(Utility.class.getName());

    private Utility() {
    }

    public static int getOption(int firstOption, int lastOption) {
        var scanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.print("Enter option: ");
                int option = scanner.nextInt();
                if (option < firstOption || option > lastOption) {
                    throw new InputMismatchException();
                }
                return option;
            } catch (InputMismatchException exception) {
                System.out.println("Wrong option. Try again");
            }
        }
    }

    public static void clearOutput() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (!(e instanceof SQLException)) {
                continue;
            }

            logger.log(System.Logger.Level.ERROR, """
                            {0}
                            SQLState: {1}
                            Error Code: {2}
                            Message: {3}""",
                    e.getStackTrace(),
                    ((SQLException) e).getSQLState(),
                    ((SQLException) e).getErrorCode(),
                    e.getMessage()
            );

            Throwable t = ex.getCause();
            while (t != null) {
                logger.log(System.Logger.Level.ERROR, "Cause: {0}", t.getCause());
                t = t.getCause();
            }
        }
    }
}
