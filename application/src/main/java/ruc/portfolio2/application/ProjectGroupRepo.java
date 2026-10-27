package ruc.portfolio2.application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProjectGroupRepo {

    public int createGroup(int studentId, int activityId,
                           String title, String supervisor)
            throws SQLException {

        if (title == null || title.isBlank()
                || supervisor == null || supervisor.isBlank()) {
            throw new IllegalArgumentException(
                    "Project title and supervisor are required."
            );
        }

        try (Connection connection = Database.connect()) {
            connection.setAutoCommit(false);

            try {
                String groupSql = """
                    INSERT INTO ProjectGroup
                        (activity_id, title, supervisor)
                    VALUES (?, ?, ?)
                    RETURNING group_id
                    """;

                int groupId;

                try (PreparedStatement statement =
                             connection.prepareStatement(groupSql)) {

                    statement.setInt(1, activityId);
                    statement.setString(2, title.trim());
                    statement.setString(3, supervisor.trim());

                    try (ResultSet result = statement.executeQuery()) {
                        if (!result.next()) {
                            throw new SQLException("No group ID returned.");
                        }
                        groupId = result.getInt("group_id");
                    }
                }

                String memberSql = """
                    INSERT INTO GroupMembership
                        (student_id, activity_id, group_id)
                    VALUES (?, ?, ?)
                    """;

                try (PreparedStatement statement =
                             connection.prepareStatement(memberSql)) {

                    statement.setInt(1, studentId);
                    statement.setInt(2, activityId);
                    statement.setInt(3, groupId);
                    statement.executeUpdate();
                }

                connection.commit();
                return groupId;

            } catch (SQLException | RuntimeException exception) {
                try {
                    connection.rollback();
                } catch (SQLException rollbackException) {
                    exception.addSuppressed(rollbackException);
                }
                throw exception;
            }
        }
    }
    public List<ProjectGroup> findByActivity(int activityId)
            throws SQLException {

        List<ProjectGroup> groups = new ArrayList<>();

        String sql = """
        SELECT group_id, activity_id, title, supervisor
        FROM ProjectGroup
        WHERE activity_id = ?
        ORDER BY title
        """;

        try (Connection connection = Database.connect();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, activityId);

            try (ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                    groups.add(new ProjectGroup(
                            result.getInt("group_id"),
                            result.getInt("activity_id"),
                            result.getString("title"),
                            result.getString("supervisor")
                    ));
                }

            }
        }

        return groups;
    }
    public void joinGroup(int studentId, int activityId, int groupId)
            throws SQLException {

        String sql = """
        INSERT INTO GroupMembership
            (student_id, activity_id, group_id)
        VALUES (?, ?, ?)
        """;

        try (Connection connection = Database.connect();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, studentId);
            statement.setInt(2, activityId);
            statement.setInt(3, groupId);

            statement.executeUpdate();
        }
    }

}