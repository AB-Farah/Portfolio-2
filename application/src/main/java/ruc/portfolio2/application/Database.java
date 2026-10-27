package ruc.portfolio2.application;

import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {

    private static final Path DATABASE_FILE =
            Path.of("verification.sqlite").toAbsolutePath().normalize();

    public static Connection connect() throws SQLException {
        if (!Files.isRegularFile(DATABASE_FILE)) {
            throw new SQLException(
                    "Database file not found: " + DATABASE_FILE
            );
        }

        Connection connection = DriverManager.getConnection(
                "jdbc:sqlite:" + DATABASE_FILE
        );

        try {
            try (Statement statement = connection.createStatement()) {
                statement.execute("PRAGMA foreign_keys = ON");
            }

            return connection;

        } catch (SQLException exception) {
            try {
                connection.close();
            } catch (SQLException closeException) {
                exception.addSuppressed(closeException);
            }

            throw exception;
        }
    }
}
