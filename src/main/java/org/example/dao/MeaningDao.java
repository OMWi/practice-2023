package org.example.dao;

import org.example.entity.Meaning;
import org.example.utils.Utility;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class MeaningDao implements IMeaningDao {
    private static Meaning extractMeaningFromResultSet(ResultSet rs) throws SQLException {
        return new Meaning(
                rs.getInt("id"),
                rs.getInt("level"),
                rs.getString("meaning")
        );
    }

    @Override
    public boolean add(int wordId, Meaning meaning) {
        try {
            var connection = DatabaseConnection.getConnection();
            try (var statement = connection.prepareStatement("INSERT INTO meaning(word_id, level, meaning) VALUES(?, ?, ?)")) {
                statement.setInt(1, wordId);
                statement.setInt(2, meaning.getLevel());
                statement.setString(3, meaning.getText());

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
    public Meaning get(int id) {
        try {
            var connection = DatabaseConnection.getConnection();
            try (var statement = connection.prepareStatement("SELECT * FROM meaning WHERE id = ?")) {
                statement.setInt(1, id);

                var rs = statement.executeQuery();
                if (rs.next()) {
                    return extractMeaningFromResultSet(rs);
                }
            }
        } catch (SQLException exception) {
            Utility.printSQLException(exception);
        }

        return null;
    }

    @Override
    public Set<Meaning> getAll() {
        try {
            var connection = DatabaseConnection.getConnection();
            try (var statement = connection.createStatement()) {
                var rs = statement.executeQuery("SELECT * FROM meaning");

                var meanings = new HashSet<Meaning>();
                while (rs.next()) {
                    var meaning = extractMeaningFromResultSet(rs);
                    meanings.add(meaning);
                }
                return meanings;
            }
        } catch (SQLException exception) {
            Utility.printSQLException(exception);
        }

        return new HashSet<>();
    }

    @Override
    public Set<Meaning> getMeaningsOfWord(int wordId) {
        try {
            var connection = DatabaseConnection.getConnection();
            try (var statement = connection.prepareStatement("SELECT * FROM meaning WHERE word_id=?")) {
                statement.setInt(1, wordId);
                var rs = statement.executeQuery();

                var meanings = new HashSet<Meaning>();
                while (rs.next()) {
                    var meaning = extractMeaningFromResultSet(rs);
                    meanings.add(meaning);
                }
                return meanings;
            }
        } catch (SQLException exception) {
            Utility.printSQLException(exception);
        }

        return new HashSet<>();
    }

    @Override
    public boolean update(int id, Meaning meaning) {
        try {
            var connection = DatabaseConnection.getConnection();
            try (var statement = connection.prepareStatement("UPDATE meaning SET level=? AND meaning=? WHERE id=?")) {
                statement.setInt(1, meaning.getLevel());
                statement.setString(2, meaning.getText());
                statement.setInt(3, id);

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
            try (var statement = connection.prepareStatement("DELETE FROM meaning WHERE id=?")) {
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
