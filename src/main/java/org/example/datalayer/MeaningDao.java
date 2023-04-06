package org.example.datalayer;

import org.example.models.Meaning;
import org.example.models.Word;
import org.example.models.WordCategory;
import org.example.utils.Utility;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MeaningDao implements IMeaningDao {
    private static Meaning extractMeaningFromResultSet(ResultSet rs) throws SQLException {
        Meaning meaning = new Meaning();
        meaning.setId(rs.getInt("id"));
        meaning.setLevel(rs.getInt("level"));
        meaning.setText(rs.getString("meaning"));
        return meaning;
    }

    @Override
    public Meaning getMeaning(int id) {
        try {
            var connection = DatabaseConnection.getConnection();
            var statement = connection.prepareStatement("SELECT * FROM meaning WHERE id = ?");
            statement.setInt(1, id);
            var rs = statement.executeQuery();

            if (rs.next()) {
                return extractMeaningFromResultSet(rs);
            }
        } catch (SQLException exception) {
            Utility.printSQLException(exception);
        }

        return null;
    }

    @Override
    public Set<Meaning> getAllMeanings() {
        try {
            var connection = DatabaseConnection.getConnection();
            var statement = connection.createStatement();
            var rs = statement.executeQuery("SELECT * FROM meaning");

            var meanings = new HashSet<Meaning>();
            while (rs.next()) {
                var meaning = extractMeaningFromResultSet(rs);
                meanings.add(meaning);
            }
            return meanings;
        } catch (SQLException exception) {
            Utility.printSQLException(exception);
        }

        return null;
    }

    @Override
    public Set<Meaning> getMeanings(int wordId) {
        try {
            var connection = DatabaseConnection.getConnection();
            var statement = connection.prepareStatement("SELECT * FROM meaning WHERE word_id=?");
            statement.setInt(1, wordId);
            var rs = statement.executeQuery();

            var meanings = new HashSet<Meaning>();
            while (rs.next()) {
                var meaning = extractMeaningFromResultSet(rs);
                meanings.add(meaning);
            }
            return meanings;
        } catch (SQLException exception) {
            Utility.printSQLException(exception);
        }

        return null;
    }

    @Override
    public boolean insertMeaning(Word word, Meaning meaning) {
        try {
            var connection = DatabaseConnection.getConnection();
            var statement = connection.prepareStatement("INSERT INTO meaning(word_id, level, meaning) VALUES(?, ?, ?)");
            statement.setInt(1, word.getId());
            statement.setInt(2, meaning.getLevel());
            statement.setString(3, meaning.getText());
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
    public boolean updateMeaning(Meaning meaning) {
        try {
            var connection = DatabaseConnection.getConnection();
            var statement = connection.prepareStatement("UPDATE meaning SET level=? AND meaning=? WHERE id=?");
            statement.setInt(1, meaning.getLevel());
            statement.setString(2, meaning.getText());
            statement.setInt(3, meaning.getId());
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
    public boolean deleteMeaning(int id) {
        try {
            var connection = DatabaseConnection.getConnection();
            var statement = connection.prepareStatement("DELETE FROM meaning WHERE id=?");
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
