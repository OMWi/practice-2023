package org.example.datalayer;

import org.example.models.WordCategory;
import org.example.utils.Utility;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class WordCategoryDao implements IWordCategory {
    private static WordCategory extractWordCategoryFromResultSet(ResultSet rs) throws SQLException {
        WordCategory wordCategory = new WordCategory();
        wordCategory.setId(rs.getInt("id"));
        wordCategory.setCategory(rs.getString("category"));
        return wordCategory;
    }

    @Override
    public WordCategory getWordCategory(int id) {
        try {
            var connection = DatabaseConnection.getConnection();
            var statement = connection.prepareStatement("SELECT * FROM word_category WHERE id = ?");
            statement.setInt(1, id);
            var rs = statement.executeQuery();

            if (rs.next()) {
                return extractWordCategoryFromResultSet(rs);
            }
        } catch (SQLException exception) {
            Utility.printSQLException(exception);
        }

        return null;
    }

    @Override
    public Set<WordCategory> getAllWordCategories() {
        try {
            var connection = DatabaseConnection.getConnection();
            var statement = connection.createStatement();
            var rs = statement.executeQuery("SELECT * FROM word_category");

            var wordCategories = new HashSet<WordCategory>();
            while (rs.next()) {
                WordCategory wordCategory = extractWordCategoryFromResultSet(rs);
                wordCategories.add(wordCategory);
            }
            return wordCategories;
        } catch (SQLException exception) {
            Utility.printSQLException(exception);
        }

        return null;
    }

    @Override
    public boolean insertWordCategory(WordCategory wordCategory) {
        try {
            var connection = DatabaseConnection.getConnection();
            var statement = connection.prepareStatement("INSERT INTO word_category(category) VALUES(?)");
            statement.setString(1, wordCategory.getCategory());
            var rowsCount = statement.executeUpdate();
            if (rowsCount == 1) {
                return true;
            }
        } catch (SQLException exception) {
            Utility.printSQLException(exception);
        }
        return false;
    }

    @Override
    public boolean updateWordCategory(WordCategory wordCategory) {
        try {
            var connection = DatabaseConnection.getConnection();
            var statement = connection.prepareStatement("UPDATE word_category SET category=? WHERE id=?");
            statement.setString(1, wordCategory.getCategory());
            statement.setInt(2, wordCategory.getId());
            var rowsCount = statement.executeUpdate();
            if (rowsCount == 1) {
                return true;
            }
        } catch (SQLException exception) {
            Utility.printSQLException(exception);
        }
        return false;
    }

    @Override
    public boolean deleteWordCategory(int id) {
        try {
            var connection = DatabaseConnection.getConnection();
            var statement = connection.prepareStatement("DELETE FROM word_category WHERE id=?");
            statement.setInt(1, id);
            var rowsCount = statement.executeUpdate();
            if (rowsCount == 1) {
                return true;
            }
        } catch (SQLException exception) {
            Utility.printSQLException(exception);
        }
        return false;
    }
}
