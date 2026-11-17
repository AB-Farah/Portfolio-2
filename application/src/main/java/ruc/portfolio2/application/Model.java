package ruc.portfolio2.application;
import java.util.ArrayList;

public class Model {
    MyDB db = new MyDB();

    Model() {
        db.cmd("PRAGMA foreign_keys = ON;");
    }

    ArrayList<String> getStudents() {
        return db.query("select name from Student order by name;", "name");
    }

    ArrayList<String> getActivites() {
        return db.query("select name from ProjectActivity order by activity_id", "name");
    }

    ArrayList<String> getGroups(String activity) {
        String activity_id = getActivity_id(activity);
        return db.query("select title from ProjectGroup where activity_id = " + activity_id + "  order by title", "title");
    }

    String getStudent_id(String Student) {
        return db.query("select student_id from Student where name = '" + Student + "';",
                "student_id").get(0);
    }

    String getActivity_id(String ProjctActivity) {
        return db.query("select activity_id from ProjectActivity where name = '" + ProjctActivity + "';",
                "activity_id").get(0);
    }

    String getGroup_id(String ProjectGroup) {
        return db.query("select group_id from ProjectGroup where title = '" + ProjectGroup + "';",
                "group_id").get(0);
    }

    void SignUp(String student, String activity) {
        String student_id = getStudent_id(student);
        String activity_id = getActivity_id(activity);
        db.cmd("insert into ActivitySignup (student_id, activity_id) values (" + student_id + ", " + activity_id + ");");
    }

    void JoinGroup(String student, String activity, String title) {
        String student_id = getStudent_id(student);
        String activity_id = getActivity_id(activity);
        String group_id = getGroup_id(title);
        db.cmd("insert into GroupMembership (student_id, activity_id, group_id) values (" + student_id + ", " + activity_id + ", " + group_id + ");");
    }

    void createGroup(String student, String activity, String title, String supervisor) {
        String activity_id = getActivity_id(activity);
        db.cmd("insert into ProjectGroup (activity_id, title, supervisor) values (" + activity_id + ", '" + title + "', '" + supervisor + "');");
        try {
            JoinGroup(student, activity, title);
        } catch (RuntimeException e) {
            db.cmd("delete from ProjectGroup where title = '" + title + "';");
            throw e;
        }
    }

}

