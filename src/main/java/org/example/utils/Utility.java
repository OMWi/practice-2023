package org.example.utils;

import java.sql.SQLException;

public final class Utility {
    private static final System.Logger logger = System.getLogger(Utility.class.getName());

    private Utility() {
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
