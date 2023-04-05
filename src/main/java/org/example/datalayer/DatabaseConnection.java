package org.example.datalayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
  private static Connection connection = null;

  static String url = "jdbc:postgresql:language-learning-app";
  static String user = "postgres";
  static String password = "573458";

  private DatabaseConnection() {}

  public static Connection getConnection() throws SQLException {
    if (connection == null) {
      connection = DriverManager.getConnection(url, user, password);
    }
    return connection;
  }
}
