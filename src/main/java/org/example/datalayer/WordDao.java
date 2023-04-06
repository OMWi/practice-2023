package org.example.datalayer;

import org.example.models.Word;
import org.example.utils.Utility;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class WordDao implements IWordDao {
    private static Word extractWordFromResultSet(ResultSet rs) throws SQLException {
        Word word = new Word();
        word.setId(rs.getInt("id"));
        word.setText(rs.getString("word"));
        var wordCategoryDao = new WordCategoryDao();
        var wordCategory = wordCategoryDao.getWordCategory(rs.getInt("word_category_id"));
        word.setCategory(wordCategory);
        return word;
    }


    @Override
    public Word getWord(int id) {
        try {
            var connection = DatabaseConnection.getConnection();
            var statement = connection.prepareStatement("SELECT * FROM word WHERE id = ?");
            statement.setInt(1, id);
            var rs = statement.executeQuery();

            if (rs.next()) {
                var word = extractWordFromResultSet(rs);
                var meaningDao = new MeaningDao();
                var meanings = meaningDao.getMeanings(word.getId());
                word.setMeanings(meanings);
                return word;
            }
        } catch (SQLException exception) {
            Utility.printSQLException(exception);
        }

        return null;
    }

    @Override
    public Set<Word> getAllWords() {
        try {
            var connection = DatabaseConnection.getConnection();
            var statement = connection.createStatement();
            var rs = statement.executeQuery("SELECT * FROM word");

            var words = new HashSet<Word>();
            var meaningDao = new MeaningDao();
            while (rs.next()) {
                var word = extractWordFromResultSet(rs);
                word.setMeanings(meaningDao.getMeanings(word.getId()));
                words.add(word);
            }
            return words;
        } catch (SQLException exception) {
            Utility.printSQLException(exception);
        }

        return null;
    }

    @Override
    public boolean insertWord(Word word) {
        try {
            var connection = DatabaseConnection.getConnection();
            var statement = connection.prepareStatement("INSERT INTO word(word, word_category_id) VALUES(?, ?)");
            statement.setString(1, word.getText());
            statement.setInt(2, word.getCategory().getId());
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
    public boolean updateWord(Word word) {
        try {
            var connection = DatabaseConnection.getConnection();
            var statement = connection.prepareStatement("UPDATE word SET word=? AND category=? WHERE id=?");
            statement.setString(1, word.getText());
            statement.setInt(2, word.getCategory().getId());
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
    public boolean deleteWord(int id) {
        try {
            var connection = DatabaseConnection.getConnection();
            var statement = connection.prepareStatement("DELETE FROM word WHERE id=?");
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
