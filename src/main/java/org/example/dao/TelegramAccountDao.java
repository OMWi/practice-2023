package org.example.dao;

import org.example.entity.TelegramAccount;
import org.example.utils.Utility;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class TelegramAccountDao implements ITelegramAccountDao {
    private static TelegramAccount extractTelegramAccountFromResultSet(ResultSet rs) throws SQLException {
        return new TelegramAccount(
                rs.getInt("user_id"),
                rs.getLong("chat_id"),
                rs.getString("username"),
                rs.getBoolean("is_confirmed")
        );
    }

    @Override
    public boolean add(TelegramAccount telegramAccount) {
        try {
            var connection = DatabaseConnection.getConnection();
            try (var statement = connection.prepareStatement("INSERT INTO telegram_account(chat_id, username) VALUES(?, ?)")) {
                statement.setLong(1, telegramAccount.getChatId());
                statement.setString(2, telegramAccount.getUsername());

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
    public TelegramAccount get(int userId) {
        try {
            var connection = DatabaseConnection.getConnection();
            try (var statement = connection.prepareStatement("SELECT * FROM telegram_account WHERE user_id = ?")) {
                statement.setInt(1, userId);

                var rs = statement.executeQuery();
                if (rs.next()) {
                    return extractTelegramAccountFromResultSet(rs);
                }
            }
        } catch (SQLException exception) {
            Utility.printSQLException(exception);
        }

        return null;
    }

    @Override
    public Set<TelegramAccount> getAll() {
        try {
            var connection = DatabaseConnection.getConnection();
            try (var statement = connection.createStatement()) {
                var rs = statement.executeQuery("SELECT * FROM telegram_account");

                var telegramAccounts = new HashSet<TelegramAccount>();
                while (rs.next()) {
                    TelegramAccount telegramAccount = extractTelegramAccountFromResultSet(rs);
                    telegramAccounts.add(telegramAccount);
                }
                return telegramAccounts;
            }
        } catch (SQLException exception) {
            Utility.printSQLException(exception);
        }

        return new HashSet<>();
    }

    @Override
    public boolean update(int userId, TelegramAccount telegramAccount) {
        try {
            var connection = DatabaseConnection.getConnection();
            try (var statement = connection.prepareStatement("UPDATE telegram_account SET chat_id=? AND username=? WHERE user_id=?")) {
                statement.setLong(1, telegramAccount.getChatId());
                statement.setString(2, telegramAccount.getUsername());

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
    public boolean remove(int userId) {
        try {
            var connection = DatabaseConnection.getConnection();
            try (var statement = connection.prepareStatement("DELETE FROM telegram_account WHERE user_id=?")) {
                statement.setInt(1, userId);

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
