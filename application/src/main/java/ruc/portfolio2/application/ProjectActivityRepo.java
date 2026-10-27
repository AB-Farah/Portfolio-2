package ruc.portfolio2.application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProjectActivityRepo {

    public List<ProjectActivity> findAll() throws SQLException {
        List<ProjectActivity> activities = new ArrayList<>();

        String sql =
                "SELECT activity_id, name FROM ProjectActivity ORDER BY name";

        try (Connection connection = Database.connect();
             Statement statement = connection.createStatement();
             ResultSet result = statement.executeQuery(sql)) {

            while (result.next()) {
                ProjectActivity activity = new ProjectActivity(
                        result.getInt("activity_id"),
                        result.getString("name")
                );

                activities.add(activity);
            }
        }

        return activities;
    }
}