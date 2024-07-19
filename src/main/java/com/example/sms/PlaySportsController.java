package com.example.sms;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class PlaySportsController {

    @FXML
    private Button viewReportBtn;

    @FXML
    private Button studentProblemFormBtn;

    @FXML
    private Button visitLibraryBtn;

    @FXML
    private Button counsellingFormBtn;

    @FXML
    private Button playSportsBtn;

    @FXML
    private Button solveMcqsBtn;

    @FXML
    private Button logout;

    @FXML
    private Label displayLoggedUser;

    @FXML
    private TextField nameField;

    @FXML
    private TextField semesterField;

    @FXML
    private TextField semesterField1;

    @FXML
    public void openViewReport(MouseEvent mouseEvent) throws IOException {
        Stage stage = (Stage) viewReportBtn.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("view-reports.fxml"));
        Parent root = loader.load();
        stage.setTitle("Student Management System - view-reports ");
        stage.setScene(new Scene(root));
    }

    @FXML
    public void openStudentProblemForm(MouseEvent mouseEvent) throws IOException {
        Stage stage = (Stage) studentProblemFormBtn.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("student-problem-form.fxml"));
        Parent root = loader.load();
        stage.setTitle("Student Management System - student-problem-form ");
        stage.setScene(new Scene(root));
    }

    @FXML
    public void openVisitLibrary(MouseEvent mouseEvent) throws IOException {
        Stage stage = (Stage) visitLibraryBtn.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("visit-library.fxml"));
        Parent root = loader.load();
        stage.setTitle("Student Management System - visit-library ");
        stage.setScene(new Scene(root));
    }

    @FXML
    public void openCounsellingForm(MouseEvent mouseEvent) throws IOException {
        Stage stage = (Stage) counsellingFormBtn.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("counselling-form.fxml"));
        Parent root = loader.load();
        stage.setTitle("Student Management System - counselling form ");
        stage.setScene(new Scene(root));
    }

    @FXML
    public void openPlaySports(MouseEvent mouseEvent) throws IOException {
        Stage stage = (Stage) playSportsBtn.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("play-sports.fxml"));
        Parent root = loader.load();
        stage.setTitle("Student Management System - play sports ");
        stage.setScene(new Scene(root));
    }

    @FXML
    public void openSolveMcqs(MouseEvent mouseEvent) throws IOException {
        Stage stage = (Stage) solveMcqsBtn.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("solve-mcqs.fxml"));
        Parent root = loader.load();
        stage.setTitle("Student Management System - solve-mcqs ");
        stage.setScene(new Scene(root));
    }

    @FXML
    public void handleLogout(MouseEvent mouseEvent) throws IOException {
        Stage stage = (Stage) logout.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
        Parent root = loader.load();
        stage.setTitle("Student Management System - login ");
        System.out.println(Login.loggedUserUsername);
        stage.setScene(new Scene(root));
    }

    @FXML
    public void onSubmit(MouseEvent mouseEvent) {
        // Get the values from the text fields
        String name = nameField.getText();
        String semester = semesterField.getText();
        String interest = semesterField1.getText();

        // Check if any field is empty
        if (name.isEmpty() || semester.isEmpty() || interest.isEmpty()) {
            showAlert("Error", "All fields must be filled out.");
            return;
        }

        // Define the CSV file path
        String filePath = "eca_records.csv";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            // Write the data to the CSV file
            writer.write(String.format("%s,%s,%s%n", name, semester, interest));

            // Show success alert
            showAlert("Success", "Record saved successfully!");

            // Clear fields after saving
            nameField.clear();
            semesterField.clear();
            semesterField1.clear();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Failed to save record.");
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
