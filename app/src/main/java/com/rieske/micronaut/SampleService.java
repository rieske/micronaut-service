package com.rieske.micronaut;

import io.micronaut.context.annotation.Context;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.sql.SQLException;
import java.util.Optional;

@Context
class SampleService {

    private final DataSource dataSource;

    private static final String INSERT_SAMPLE_SQL = "INSERT INTO Sample VALUES(?, ?)";
    private static final String SELECT_SAMPLE_SQL = "SELECT value FROM Sample WHERE id=?";
    private static final String DELETE_SAMPLE_SQL = "DELETE FROM Sample WHERE id=?";

    SampleService(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    void addSample(int id, String value) {
        try (var connection = dataSource.getConnection()) {
            connection.setAutoCommit(false);
            try (var statement = connection.prepareStatement(INSERT_SAMPLE_SQL)) {
                statement.setInt(1, id);
                statement.setString(2, value);
                statement.executeUpdate();
            }
            connection.commit();
        } catch (SQLException e) {
            throw new UncheckedIOException(new IOException(e));
        }
    }

    Optional<String> getSample(int id) {
        try (var connection = dataSource.getConnection()) {
            try (var statement = connection.prepareStatement(SELECT_SAMPLE_SQL)) {
                statement.setInt(1, id);
                try (var resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        return Optional.of(resultSet.getString(1));
                    }
                    return Optional.empty();
                }
            }
        } catch (SQLException e) {
            throw new UncheckedIOException(new IOException(e));
        }
    }

    void deleteSample(int id) {
        try (var connection = dataSource.getConnection()) {
            connection.setAutoCommit(false);
            try (var statement = connection.prepareStatement(DELETE_SAMPLE_SQL)) {
                statement.setInt(1, id);
                statement.executeUpdate();
            }
            connection.commit();
        } catch (SQLException e) {
            throw new UncheckedIOException(new IOException(e));
        }
    }
}
