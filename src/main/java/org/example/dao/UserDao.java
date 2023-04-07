package org.example.dao;

import org.example.entity.User;
import org.example.enums.UserRole;
import org.example.utils.Utility;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class UserDao implements IUserDao {
    public static User extractUserFromResultSet(ResultSet rs) throws SQLException {
        return new User(
                rs.getInt("id"),
                rs.getString("email"),
                rs.getString("hashed"),
                rs.getString("salt"),
                UserRole.valueOf(rs.getString("role"))
        );
    }

    @Override
    public boolean add(User user) {
        try {
            var connection = DatabaseConnection.getConnection();
            try (var statement = connection.prepareStatement("INSERT INTO custom_user(email, hashed, salt, role) VALUES(?, ?, ?, ?::role)")) {
                statement.setString(1, user.getEmail());
                statement.setString(2, user.getHashed());
                statement.setString(3, user.getSalt());
                statement.setString(4, user.getRole().toString());

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
    public User get(int id) {
        try {
            var connection = DatabaseConnection.getConnection();
            try (var statement = connection.prepareStatement("SELECT * FROM custom_user WHERE id = ?")) {
                statement.setInt(1, id);

                var rs = statement.executeQuery();
                if (rs.next()) {
                    return extractUserFromResultSet(rs);
                }
            }
        } catch (SQLException exception) {
            Utility.printSQLException(exception);
        }

        return null;
    }

    @Override
    public User getByEmail(String email) {
        try {
            var connection = DatabaseConnection.getConnection();
            try (var statement = connection.prepareStatement("SELECT * FROM custom_user WHERE email = ?")) {
                statement.setString(1, email);

                var rs = statement.executeQuery();
                if (rs.next()) {
                    return extractUserFromResultSet(rs);
                }
            }
        } catch (SQLException exception) {
            Utility.printSQLException(exception);
        }

        return null;
    }

    @Override
    public Set<User> getAll() {
        try {
            var connection = DatabaseConnection.getConnection();
            try (var statement = connection.createStatement()) {
                var rs = statement.executeQuery("SELECT * FROM custom_user");

                Set<User> users = new HashSet<>();
                while (rs.next()) {
                    User user = extractUserFromResultSet(rs);
                    users.add(user);
                }
                return users;
            }
        } catch (SQLException exception) {
            Utility.printSQLException(exception);
        }

        return new HashSet<>();
    }

    @Override
    public boolean update(User user) {
        try {
            var connection = DatabaseConnection.getConnection();
            try (var statement = connection.prepareStatement("UPDATE custom_user SET email=? AND hashed=? AND salt=? AND role=? WHERE id=?")) {
                statement.setString(1, user.getEmail());
                statement.setString(2, user.getHashed());
                statement.setString(3, user.getSalt());
                statement.setString(4, user.getRole().toString());
                statement.setInt(5, user.getId());

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
    public boolean remove(int id) {
        try {
            var connection = DatabaseConnection.getConnection();
            try (var statement = connection.prepareStatement("DELETE FROM custom_user WHERE id=?")) {
                statement.setInt(1, id);

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
