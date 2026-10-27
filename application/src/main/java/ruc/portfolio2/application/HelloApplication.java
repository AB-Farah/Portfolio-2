package ruc.portfolio2.application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Button;
import java.sql.SQLException;
import javafx.scene.control.TextField;
import javafx.geometry.Insets;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) {
        Label heading = new Label("Project Activity Signup");
        ComboBox<ProjectActivity> activityBox = new ComboBox<>();
        activityBox.setPromptText("Select project activity");
        ComboBox<Student> studentbox = new ComboBox<>();
        studentbox.setPromptText("Select student");

        ProjectActivityRepo activityRepo = new ProjectActivityRepo();
        StudentRepo studentRepo = new StudentRepo();
        ActivitySignupRepo signupRepo = new ActivitySignupRepo();

        try {
            activityBox.getItems().setAll(activityRepo.findAll());
            studentbox.getItems().setAll(studentRepo.findAll());
            System.out.println("Running: " + getClass().getName());
            System.out.println("Loaded students: " + studentbox.getItems());

        }catch (SQLException exception){ exception.printStackTrace();}


        Button signupButton = new Button("Sign up");
        Label feedback = new Label();


        signupButton.setOnAction(event -> {
            ProjectActivity selectedActivity = activityBox.getValue();
            Student selectedStudent = studentbox.getValue();

            if (selectedStudent == null || selectedActivity == null){
                feedback.setText("You need to select a student and an activity.");
                return;
            }

            try {
                boolean created = signupRepo.signUp(
                        selectedStudent.getId(),
                        selectedActivity.getId()
                );

                if (created) {
                    feedback.setText("Signup saved.");
                } else {
                    feedback.setText(
                            "This student is already signed up for this activity."
                    );
                }
            } catch (SQLException exception) {
                feedback.setText("Could not save signup.");
                exception.printStackTrace();
            }
        });

        Label groupHeading = new Label("Create project group");

        TextField titleField = new TextField();
        titleField.setPromptText("Project title");

        TextField supervisorField = new TextField();
        supervisorField.setPromptText("Supervisor");

        Button createGroupButton = new Button("Create group");

        ProjectGroupRepo groupRepo = new ProjectGroupRepo();

        createGroupButton.setOnAction(event -> {
            Student student = studentbox.getValue();
            ProjectActivity activity = activityBox.getValue();

            if (student == null || activity == null) {
                feedback.setText("Please select a student and an activity.");
                return;
            }

            try {
                int groupId = groupRepo.createGroup(
                        student.getId(),
                        activity.getId(),
                        titleField.getText(),
                        supervisorField.getText()
                );

                feedback.setText("Group created. ID: " + groupId);
                titleField.clear();
                supervisorField.clear();

            } catch (IllegalArgumentException exception) {
                feedback.setText(exception.getMessage());

            } catch (SQLException exception) {
                feedback.setText(
                        "Could not create group. Check signup, existing membership "
                                + "and whether the title is already used."
                );
                exception.printStackTrace();
            }
        });

        ProjectGroupRepo GroupRepo = new ProjectGroupRepo();
        Label joinHeading = new Label("Join existing group");

        ComboBox<ProjectGroup> groupBox = new ComboBox<>();
        groupBox.setPromptText("Select project group");

        activityBox.setOnAction(event -> {
            groupBox.getSelectionModel().clearSelection();
            groupBox.getItems().clear();

            ProjectActivity activity = activityBox.getValue();

            if (activity != null) {
                try {
                    groupBox.getItems().setAll(
                            groupRepo.findByActivity(activity.getId())
                    );
                } catch (SQLException exception) {
                    feedback.setText("Could not load project groups.");
                    exception.printStackTrace();
                }
            }
        });

        Button joinGroupButton = new Button("Join group");

        joinGroupButton.setOnAction(event -> {
            Student student = studentbox.getValue();
            ProjectActivity activity = activityBox.getValue();
            ProjectGroup group = groupBox.getValue();

            if (student == null || activity == null || group == null) {
                feedback.setText("Please select a student, activity and group.");
                return;
            }

            try {
                groupRepo.joinGroup(
                        student.getId(),
                        activity.getId(),
                        group.getId()
                );

                feedback.setText("You joined: " + group.getTitle());

            } catch (SQLException exception) {
                feedback.setText(
                        "Could not join group. Check activity signup "
                                + "and existing group membership."
                );
                exception.printStackTrace();
            }
        });



        VBox root = new VBox(10);
        root.getChildren().addAll(heading, activityBox, studentbox, signupButton,
                groupHeading, titleField, supervisorField, createGroupButton,
                joinHeading, groupBox, joinGroupButton, feedback );
        VBox.setMargin(joinHeading, new Insets(25, 0, 0, 0));
        Scene scene = new Scene(root, 600, 550);

        stage.setTitle("Portfolio 2");
        stage.setScene(scene);
        stage.show();
    }
}