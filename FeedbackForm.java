import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class FeedbackForm extends Application {

    // Database
    private static final String URL = "jdbc:mysql://localhost:3306/feedbackdb";
    private static final String USER = "root";
    private static final String PASS = "sakshi123";

    @Override
    public void start(Stage stage) {

        // TITLE 
        Label title = new Label("Student Feedback Form");
        title.setStyle("-fx-font-size: 26px; -fx-font-weight: bold; -fx-text-fill: #2C3E50;");

        //  TEXT FIELDS 
        TextField nameField = buildTextField("Enter your name");
        TextField emailField = buildTextField("Enter your email");
        TextField rollField = buildTextField("Enter your roll number");
        TextField deptField = buildTextField("Enter your department (CSE/IT/ECE)");
        TextField semField = buildTextField("Enter your semester");
        TextField courseField = buildTextField("Enter course name");
        TextField courseCodeField = buildTextField("Enter course code");
        TextField facultyField = buildTextField("Enter faculty name");

        // RATING 
        Label ratingLabel = new Label("Rate the Course:");
        ratingLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");

        ToggleGroup ratingGroup = new ToggleGroup();

        RadioButton r1 = new RadioButton("1");
        RadioButton r2 = new RadioButton("2");
        RadioButton r3 = new RadioButton("3");
        RadioButton r4 = new RadioButton("4");
        RadioButton r5 = new RadioButton("5");

        r1.setToggleGroup(ratingGroup);
        r2.setToggleGroup(ratingGroup);
        r3.setToggleGroup(ratingGroup);
        r4.setToggleGroup(ratingGroup);
        r5.setToggleGroup(ratingGroup);

        HBox ratingBox = new HBox(15, r1, r2, r3, r4, r5);
        ratingBox.setPadding(new Insets(5, 0, 10, 0));

        //  COMMENTS 
        TextArea commentsArea = new TextArea();
        commentsArea.setPromptText("Write your comments...");
        commentsArea.setPrefRowCount(4);

        // SUGGESTION BOX 
        TextArea suggestionArea = new TextArea();
        suggestionArea.setPromptText("Any suggestions for improvement...");
        suggestionArea.setPrefRowCount(3);

        // ---------- SUBMIT BUTTON ----------
        Button submitBtn = new Button("Submit Feedback");
        submitBtn.setStyle(
                "-fx-background-color: #3498DB; " +
                "-fx-text-fill: white; " +
                "-fx-font-size: 14px; " +
                "-fx-padding: 10 20 10 20; " +
                "-fx-background-radius: 8;"
        );

        submitBtn.setOnAction(e -> {
            String name = nameField.getText();
            String email = emailField.getText();
            String roll = rollField.getText();
            String dept = deptField.getText();
            String sem = semField.getText();
            String course = courseField.getText();
            String courseCode = courseCodeField.getText();
            String faculty = facultyField.getText();

            RadioButton selected = (RadioButton) ratingGroup.getSelectedToggle();
            String rating = selected != null ? selected.getText() : null;

            String comments = commentsArea.getText();
            String suggestion = suggestionArea.getText();

            saveToDatabase(name, email, roll, dept, sem, course, courseCode, faculty, rating, comments, suggestion);
        });

        // LAYOUT 
        VBox form = new VBox(15,
                title, nameField, emailField, rollField, deptField, semField,
                courseField, courseCodeField, facultyField,
                ratingLabel, ratingBox,
                commentsArea, suggestionArea,
                submitBtn
        );

        form.setPadding(new Insets(25));
        form.setAlignment(Pos.TOP_CENTER);
        form.setStyle("-fx-background-color: #F8F9FA; -fx-border-radius: 10; -fx-background-radius: 10;");

        Scene scene = new Scene(form, 500, 700);

        stage.setTitle("Feedback Collector System");
        stage.setScene(scene);
        stage.show();
    }

    private void saveToDatabase(String name, String email, String roll, String dept, String sem,
                                String course, String courseCode, String faculty,
                                String rating, String comments, String suggestion) {

        String sql = "INSERT INTO feedback (name, email, roll, dept, semester, course, course_code, faculty, rating, comments, suggestion) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.setString(3, roll);
            stmt.setString(4, dept);
            stmt.setString(5, sem);
            stmt.setString(6, course);
            stmt.setString(7, courseCode);
            stmt.setString(8, faculty);
            stmt.setString(9, rating);
            stmt.setString(10, comments);
            stmt.setString(11, suggestion);

            stmt.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("Feedback submitted successfully!");
            alert.showAndWait();

        } catch (Exception ex) {
            ex.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Database Error");
            alert.setHeaderText(null);
            alert.setContentText("Failed to submit feedback. Check console.");
            alert.showAndWait();
        }
    }

    private TextField buildTextField(String placeholder) {
        TextField tf = new TextField();
        tf.setPromptText(placeholder);
        tf.setPrefHeight(35);
        return tf;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
