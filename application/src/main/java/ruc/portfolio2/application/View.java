package ruc.portfolio2.application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.ArrayList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;


public class View extends Application {
    private Model model = new Model();
    private Controller con = new Controller(model, this);
    private ComboBox<String> studentBox = new ComboBox<>();
    private ComboBox<String> activityBox = new ComboBox<>();
    private Button signupButton = new Button("Sign up");
    private Label feedback = new Label();

    void setStudents(ArrayList<String> list) {
        studentBox.getItems().setAll(list);
    }

    void setActivities(ArrayList<String> list) {
        activityBox.getItems().setAll(list);
    }

   void setFeedback(String s) { feedback.setText(s); }

    @Override
    public void start(Stage stage) {
        con.loadLists();
            signupButton.setOnAction(e -> con.signUp(studentBox.getValue(), activityBox.getValue()));
            VBox root = new VBox(10, studentBox, activityBox, signupButton, feedback);
            Scene scene = new Scene(root, 600, 550);
            stage.setTitle("Portfolio 2");
            stage.setScene(scene);
            stage.show();
        }
    }
