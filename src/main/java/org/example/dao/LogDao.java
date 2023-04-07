package org.example.dao;

import org.example.entity.Log;
import org.example.enums.LogType;
import org.example.utils.Utility;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class LogDao implements ILogDao {
    private static Log extractLogFromResultSet(ResultSet rs) throws SQLException {
        return new Log(
                rs.getInt("id"),
                rs.getInt("user_id"),
                LogType.valueOf(rs.getString("type")),
                rs.getObject("created_at", LocalDateTime.class)
        );
    }

    @Override
    public boolean add(Log log) {
        try {
            var connection = DatabaseConnection.getConnection();
            try (var statement = connection.prepareStatement("INSERT INTO log(user_id, type, created_at) VALUES(?, ?, ?)")) {
                statement.setInt(1, log.getUserId());
                statement.setString(2, log.getType().toString());
//            todo: check assignment below
                statement.setObject(3, log.getCreatedAt(), LocalDateTime.class.getModifiers());

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
    public Log get(int id) {
        try {
            var connection = DatabaseConnection.getConnection();
            try (var statement = connection.prepareStatement("SELECT * FROM log WHERE id = ?")) {
                statement.setInt(1, id);

                var rs = statement.executeQuery();
                if (rs.next()) {
                    return extractLogFromResultSet(rs);
                }
            }
        } catch (SQLException exception) {
            Utility.printSQLException(exception);
        }

        return null;
    }

    @Override
    public Set<Log> getAll() {
        try {
            var connection = DatabaseConnection.getConnection();
            try (var statement = connection.prepareStatement("SELECT * FROM log")) {
                var rs = statement.executeQuery();
                var logs = new HashSet<Log>();
                while (rs.next()) {
                    var log = extractLogFromResultSet(rs);
                    logs.add(log);
                }
                return logs;
            }
        } catch (SQLException exception) {
            Utility.printSQLException(exception);
        }

        return new HashSet<>();
    }

    @Override
    public boolean remove(int id) {
        try {
            var connection = DatabaseConnection.getConnection();
            try (var statement = connection.prepareStatement("DELETE FROM log WHERE id=?")) {
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
