package org.example.dao;

import org.example.entity.UserData;
import org.example.utils.Utility;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;

public class UserDataDao implements IUserDataDao {
    private static UserData extractUserDataFromResultSet(ResultSet rs) throws SQLException {
        var telegramAccountDao = new TelegramAccountDao();
        var wordDao = new WordDao();
        var wordlistDao = new WordListDao();
        var userId = rs.getInt("user_id");
        return new UserData(
                userId,
                rs.getString("username"),
                rs.getInt("points"),
                telegramAccountDao.get(userId),
                wordDao.getByUser(userId),
                wordlistDao.getByUser(userId)
        );
    }

    @Override
    public boolean add(UserData userData) {
        try {
            var connection = DatabaseConnection.getConnection();
            try (var statement = connection.prepareStatement("INSERT INTO user_data(user_id, username) VALUES(?, ?)")) {
                statement.setInt(1, userData.getUserId());
                statement.setString(2, userData.getUsername());

                var rowsCount = statement.executeUpdate();
                if (rowsCount == 1) {
                    return true;
                }
            }
        } catch (SQLException exception) {
            Utility.printSQLException(exception);
        }

        return false;
    }

    @Override
    public boolean addWord(int userId, int wordId) {
        try {
            var connection = DatabaseConnection.getConnection();
            try (var statement = connection.prepareStatement("INSERT INTO userdata_word(userdata_id, word_id) VALUES(?, ?)")) {
                statement.setInt(1, userId);
                statement.setInt(2, wordId);

                var rowsCount = statement.executeUpdate();
                if (rowsCount == 1) {
                    return true;
                }
            }
        } catch (SQLException exception) {
            Utility.printSQLException(exception);
        }

        return false;
    }

    @Override
    public UserData get(int userId) {
        try {
            var connection = DatabaseConnection.getConnection();
            try (var statement = connection.prepareStatement("SELECT * FROM user_data WHERE user_id=?")) {
                statement.setInt(1, userId);

                var rs = statement.executeQuery();
                if (rs.next()) {
                    return extractUserDataFromResultSet(rs);
                }
            }
        } catch (SQLException exception) {
            Utility.printSQLException(exception);
        }

        return null;
    }

    @Override
    public HashSet<UserData> getAll() {
        try {
            var connection = DatabaseConnection.getConnection();
            try (var statement = connection.prepareStatement("SELECT * FROM user_data")) {
                var rs = statement.executeQuery();
                var userDataSet = new HashSet<UserData>();
                if (rs.next()) {
                    var userData = extractUserDataFromResultSet(rs);
                    userDataSet.add(userData);
                }
                return userDataSet;
            }
        } catch (SQLException exception) {
            Utility.printSQLException(exception);
        }

        return new HashSet<>();
    }

    @Override
    public boolean update(int userId, UserData userData) {
        try {
            var connection = DatabaseConnection.getConnection();
            try (var statement = connection.prepareStatement("UPDATE user_data SET username=? AND points=? WHERE user_id=?")) {
                statement.setString(1, userData.getUsername());
                statement.setInt(2, userData.getPoints());
                statement.setInt(3, userId);

                var rowsCount = statement.executeUpdate();
                if (rowsCount == 1) {
                    return true;
                }
            }
        } catch (SQLException exception) {
            Utility.printSQLException(exception);
        }

        return false;
    }

}
