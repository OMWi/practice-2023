package org.example.utils;


import org.example.model.UserData;

import java.sql.Date;
import java.sql.SQLException;

public final class Utility {
    private static final System.Logger logger = System.getLogger(Utility.class.getName());

    private Utility() {
    }

    public static boolean isSubscriber(UserData userData) {
        var today = new Date(System.currentTimeMillis());
        return userData.getSubscriptionExpirationDate() != null && userData.getSubscriptionExpirationDate().compareTo(today) > 0;
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
