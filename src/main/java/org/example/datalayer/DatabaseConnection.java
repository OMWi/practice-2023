import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
  private static Connection connection = null;

  static String url = "jdbc:postgresql://localhost/<dbname>";
  static String user = "<user>";
  static String password = "<password>";

//  TODO: refactor this class
//  private DatabaseConnection() {
//  }

  public static Connection getConnection() {
    if (connection == null) {
      connection = DriverManager.getConnection(url, user, password);
    }
    return connection;
  }
}
