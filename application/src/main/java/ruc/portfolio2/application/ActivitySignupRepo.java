package ruc.portfolio2.application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ActivitySignupRepo {

    public boolean signUp(int studentId, int activityId)
            throws SQLException {

        String sql = """
            INSERT INTO ActivitySignup (student_id, activity_id)
            VALUES (?, ?)
            ON CONFLICT(student_id, activity_id) DO NOTHING
            """;

        try (Connection connection = Database.connect();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, studentId);
            statement.setInt(2, activityId);

            return statement.executeUpdate() == 1;
        }
    }
}