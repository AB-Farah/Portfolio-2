package ruc.portfolio2.application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StudentRepo {

    public List<Student> findAll() throws SQLException {
        List<Student> students = new ArrayList<>();

        String sql = "SELECT student_id, name FROM Student ORDER BY name";

        try (Connection connection = Database.connect();
             Statement statement = connection.createStatement();
             ResultSet result = statement.executeQuery(sql)) {

            System.out.println(
                    "Database URL: " + connection.getMetaData().getURL()
            );

            while (result.next()) {
                Student student = new Student(
                        result.getInt("student_id"),
                        result.getString("name")
                );

                students.add(student);
                System.out.println(
                        "Student row: " + student.getId() + " / " + student.getName()
                );
            }
        }

        return students;
    }
}