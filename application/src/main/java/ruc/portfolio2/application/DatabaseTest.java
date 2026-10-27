package ruc.portfolio2.application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseTest {

    public static void main(String[] args) {
        try (Connection connection = Database.connect();
             Statement statement = connection.createStatement();
             ResultSet result = statement.executeQuery(
                     "SELECT activity_id, name FROM ProjectActivity ORDER BY activity_id"
             )) {

            while (result.next()) {
                System.out.println(
                        result.getInt("activity_id") + ": "
                                + result.getString("name")
                );
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
}