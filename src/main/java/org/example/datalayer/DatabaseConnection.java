package org.example.datalayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
  private static Connection connection = null;

  static String url = "jdbc:postgresql://localhost/<dbname>";
  static String user = "<user>";
  static String password = "<password>";

  private DatabaseConnection() {}

  public static Connection getConnection() throws SQLException {
    if (connection == null) {
      connection = DriverManager.getConnection(url, user, password);
    }
    return connection;
  }
}
