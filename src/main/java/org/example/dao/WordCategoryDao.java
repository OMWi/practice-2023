package org.example.dao;

import org.example.entity.WordCategory;
import org.example.utils.Utility;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;

public class WordCategoryDao implements IWordCategory {
    private static WordCategory extractWordCategoryFromResultSet(ResultSet rs) throws SQLException {
        return new WordCategory(
                rs.getInt("id"),
                rs.getString("category")
        );
    }

    @Override
    public boolean add(WordCategory wordCategory) {
        try {
            var connection = DatabaseConnection.getConnection();
            try (var statement = connection.prepareStatement("INSERT INTO word_category(category) VALUES(?)")) {
                statement.setString(1, wordCategory.getCategory());

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
    public WordCategory get(int id) {
        try {
            var connection = DatabaseConnection.getConnection();
            try (var statement = connection.prepareStatement("SELECT * FROM word_category WHERE id = ?")) {
                statement.setInt(1, id);

                var rs = statement.executeQuery();
                if (rs.next()) {
                    return extractWordCategoryFromResultSet(rs);
                }
            }
        } catch (SQLException exception) {
            Utility.printSQLException(exception);
        }

        return null;
    }

    @Override
    public HashSet<WordCategory> getAll() {
        try {
            var connection = DatabaseConnection.getConnection();
            try (var statement = connection.createStatement()) {
                var rs = statement.executeQuery("SELECT * FROM word_category");

                var wordCategories = new HashSet<WordCategory>();
                while (rs.next()) {
                    WordCategory wordCategory = extractWordCategoryFromResultSet(rs);
                    wordCategories.add(wordCategory);
                }
                return wordCategories;
            }
        } catch (SQLException exception) {
            Utility.printSQLException(exception);
        }

        return new HashSet<>();
    }

    @Override
    public boolean update(int id, WordCategory wordCategory) {
        try {
            var connection = DatabaseConnection.getConnection();
            try (var statement = connection.prepareStatement("UPDATE word_category SET category=? WHERE id=?")) {
                statement.setString(1, wordCategory.getCategory());
                statement.setInt(2, id);

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
            try (var statement = connection.prepareStatement("DELETE FROM word_category WHERE id=?")) {
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
