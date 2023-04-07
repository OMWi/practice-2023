package org.example.dao;

import org.example.entity.Word;
import org.example.utils.Utility;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;

public class WordDao implements IWordDao {
    private static Word extractWordFromResultSet(ResultSet rs) throws SQLException {
        var wordCategoryDao = new WordCategoryDao();
        var meaningDao = new MeaningDao();
        return new Word(
                rs.getInt("id"),
                rs.getString("word"),
                wordCategoryDao.get(rs.getInt("word_category_id")),
                meaningDao.getMeaningsOfWord(rs.getInt("id"))
        );
    }

    @Override
    public boolean add(Word word) {
        try {
            var connection = DatabaseConnection.getConnection();
            try (var statement = connection.prepareStatement("INSERT INTO word(word, word_category_id) VALUES(?, ?)")) {
                statement.setString(1, word.getText());
                statement.setInt(2, word.getCategory().getId());
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
    public Word get(int id) {
        try {
            var connection = DatabaseConnection.getConnection();
            try (var statement = connection.prepareStatement("SELECT * FROM word WHERE id = ?")) {
                statement.setInt(1, id);
                var rs = statement.executeQuery();

                if (rs.next()) {
                    var word = extractWordFromResultSet(rs);
                    var meaningDao = new MeaningDao();
                    var meanings = meaningDao.getMeaningsOfWord(word.getId());
                    word.setMeanings(meanings);
                    return word;
                }
            }
        } catch (SQLException exception) {
            Utility.printSQLException(exception);
        }

        return null;
    }

    @Override
    public HashSet<Word> getAll() {
        try {
            var connection = DatabaseConnection.getConnection();
            try (var statement = connection.createStatement()) {
                var rs = statement.executeQuery("SELECT * FROM word");

                var words = new HashSet<Word>();
                var meaningDao = new MeaningDao();
                while (rs.next()) {
                    var word = extractWordFromResultSet(rs);
                    word.setMeanings(meaningDao.getMeaningsOfWord(word.getId()));
                    words.add(word);
                }
                return words;
            }
        } catch (SQLException exception) {
            Utility.printSQLException(exception);
        }

        return new HashSet<>();
    }

    @Override
    public HashSet<Word> getByWordList(int wordListId) {
        try {
            var connection = DatabaseConnection.getConnection();
            try (var statement = connection.prepareStatement("""
                    SELECT * FROM word
                    LEFT JOIN wordlist_word
                    ON wordlist_word.word_id = word.id
                    WHERE wordlist_word.wordlist_id = ?""")
            ) {
                statement.setInt(1, wordListId);
                var rs = statement.executeQuery();

                var words = new HashSet<Word>();
                var meaningDao = new MeaningDao();
                while (rs.next()) {
                    var word = extractWordFromResultSet(rs);
                    word.setMeanings(meaningDao.getMeaningsOfWord(word.getId()));
                    words.add(word);
                }
                return words;
            }
        } catch (SQLException exception) {
            Utility.printSQLException(exception);
        }

        return new HashSet<>();
    }

    @Override
    public HashSet<Word> getByUser(int userId) {
        try {
            var connection = DatabaseConnection.getConnection();
            try (var statement = connection.prepareStatement("""
                    SELECT * FROM word
                    LEFT JOIN userdata_word
                    ON userdata_word.word_id = word.id
                    WHERE userdata_word.userdata_id = ?""")
            ) {
                statement.setInt(1, userId);
                var rs = statement.executeQuery();

                var words = new HashSet<Word>();
                var meaningDao = new MeaningDao();
                while (rs.next()) {
                    var word = extractWordFromResultSet(rs);
                    word.setMeanings(meaningDao.getMeaningsOfWord(word.getId()));
                    words.add(word);
                }
                return words;
            }

        } catch (SQLException exception) {
            Utility.printSQLException(exception);
        }

        return new HashSet<>();
    }

    @Override
    public boolean update(Word word) {
        try {
            var connection = DatabaseConnection.getConnection();
            try (var statement = connection.prepareStatement("UPDATE word SET word=? AND category=? WHERE id=?")) {
                statement.setString(1, word.getText());
                statement.setInt(2, word.getCategory().getId());
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
            try (var statement = connection.prepareStatement("DELETE FROM word WHERE id=?")) {
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
