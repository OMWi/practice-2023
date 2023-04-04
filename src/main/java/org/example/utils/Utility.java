package org.example.utils;

import java.sql.SQLException;

public final class Utility {
    private Utility() {
    }

    public static void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (!(e instanceof SQLException)) {
                continue;
            }
            e.printStackTrace(System.err);
            System.err.println("SQLState: " + ((SQLException)e).getSQLState());

            System.err.println("Error Code: " + ((SQLException)e).getErrorCode());

            System.err.println("Message: " + e.getMessage());

            Throwable t = ex.getCause();
            while(t != null) {
                System.out.println("Cause: " + t);
                t = t.getCause();
            }
        }
    }
}
