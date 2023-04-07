package org.example.dao;

import org.example.entity.WordList;
import org.example.utils.Utility;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class WordListDao implements IWordListDao {
    public static WordList extractWordListFromResultSet(ResultSet rs) throws SQLException {
        var wordDao = new WordDao();
        return new WordList(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getInt("word_count"),
                rs.getInt("popularity"),
                wordDao.getByWordList(rs.getInt("id"))
        );
    }

    @Override
    public WordList get(int id) {
        try {
            var connection = DatabaseConnection.getConnection();
            try (var statement = connection.prepareStatement("SELECT * FROM wordlist WHERE id = ?")) {
                statement.setInt(1, id);
                var rs = statement.executeQuery();

                if (rs.next()) {
                    var wordList = extractWordListFromResultSet(rs);
                    var wordDao = new WordDao();
                    wordList.setWords(wordDao.getByWordList(wordList.getId()));
                    return wordList;
                }
            }
        } catch (SQLException exception) {
            Utility.printSQLException(exception);
        }

        return null;
    }

    @Override
    public Set<WordList> getByUser(int userId) {
        try {
            var connection = DatabaseConnection.getConnection();
            try (var statement = connection.prepareStatement("""
                    SELECT * FROM wordlist
                    LEFT JOIN userdata_wordlist
                    ON userdata_wordlist.wordlist_id = wordlist.id
                    WHERE userdata_wordlist.user_id = ?""")
            ) {
                statement.setInt(1, userId);

                var rs = statement.executeQuery();
                var wordLists = new HashSet<WordList>();
                while (rs.next()) {
                    var word = extractWordListFromResultSet(rs);
                    wordLists.add(word);
                }
                return wordLists;
            }
        } catch (SQLException exception) {
            Utility.printSQLException(exception);
        }

        return null;
    }

    @Override
    public Set<WordList> getAll() {
        try {
            var connection = DatabaseConnection.getConnection();
            try (var statement = connection.createStatement()) {
                var rs = statement.executeQuery("SELECT * FROM wordlist");

                var wordLists = new HashSet<WordList>();
                var wordDao = new WordDao();
                while (rs.next()) {
                    var wordList = extractWordListFromResultSet(rs);
                    wordList.setWords(wordDao.getByWordList(wordList.getId()));
                    wordLists.add(wordList);
                }
                return wordLists;
            }
        } catch (SQLException exception) {
            Utility.printSQLException(exception);
        }

        return null;
    }

    @Override
    public boolean add(WordList wordList) {
        try {
            var connection = DatabaseConnection.getConnection();
            try (var statement = connection.prepareStatement("INSERT INTO wordlist(name, word_count, popularity) VALUES(?, ?, ?)")) {
                statement.setString(1, wordList.getName());
                statement.setInt(2, wordList.getWordCount());
                statement.setInt(3, wordList.getPopularity());

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
    public boolean update(int id, WordList wordList) {
        try {
            var connection = DatabaseConnection.getConnection();
            try (var statement = connection.prepareStatement("UPDATE wordlist SET name=? AND word_count=? AND popularity=? WHERE id=?")) {
                statement.setString(1, wordList.getName());
                statement.setInt(2, wordList.getWordCount());
                statement.setInt(3, wordList.getPopularity());
                statement.setInt(4, id);

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
            try (var statement = connection.prepareStatement("DELETE FROM wordlist WHERE id=?")) {
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
