package com.example.sms.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class StudentProblemFormController {

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
    private TextField problemTitle;

    @FXML
    private TextArea problem;

    @FXML
    private TextField studentId;

    @FXML
    private Button problemSubmitBtn;

    @FXML
    private Label message;

    @FXML
    public void openViewReport(MouseEvent mouseEvent) throws IOException {
        Stage stage = (Stage) viewReportBtn.getScene().getWindow(); // Cast this window to become a stage

        FXMLLoader loader = new FXMLLoader(getClass().getResource("view-reports.fxml"));
        Parent root = loader.load();
        stage.setTitle("Student Management System - view-reports ");
        stage.setScene(new Scene(root));
    }

    @FXML
    public void openStudentProblemForm(MouseEvent mouseEvent) throws IOException {
        Stage stage = (Stage) studentProblemFormBtn.getScene().getWindow(); // Cast this window to become a stage

        FXMLLoader loader = new FXMLLoader(getClass().getResource("student-problem-form.fxml"));
        Parent root = loader.load();
        stage.setTitle("Student Management System - student-problem-form ");
        stage.setScene(new Scene(root));
    }

    @FXML
    public void openVisitLibrary(MouseEvent mouseEvent) throws IOException {
        Stage stage = (Stage) visitLibraryBtn.getScene().getWindow(); // Cast this window to become a stage

        FXMLLoader loader = new FXMLLoader(getClass().getResource("visit-library.fxml"));
        Parent root = loader.load();
        stage.setTitle("Student Management System - visit-library ");
        stage.setScene(new Scene(root));
    }

    @FXML
    public void openCounsellingForm(MouseEvent mouseEvent) throws IOException {
        Stage stage = (Stage) counsellingFormBtn.getScene().getWindow(); // Cast this window to become a stage

        FXMLLoader loader = new FXMLLoader(getClass().getResource("counselling-form.fxml"));
        Parent root = loader.load();
        stage.setTitle("Student Management System - login ");
        stage.setScene(new Scene(root));
    }

    @FXML
    public void openPlaySports(MouseEvent mouseEvent) throws IOException {
        Stage stage = (Stage) playSportsBtn.getScene().getWindow(); // Cast this window to become a stage

        FXMLLoader loader = new FXMLLoader(getClass().getResource("play-sports.fxml"));
        Parent root = loader.load();
        stage.setTitle("Student Management System - login ");
        stage.setScene(new Scene(root));
    }

    @FXML
    public void openSolveMcqs(MouseEvent mouseEvent) throws IOException {
        Stage stage = (Stage) solveMcqsBtn.getScene().getWindow(); // Cast this window to become a stage

        FXMLLoader loader = new FXMLLoader(getClass().getResource("solve-mcqs.fxml"));
        Parent root = loader.load();
        stage.setTitle("Student Management System - solve-mcqs ");
        stage.setScene(new Scene(root));
    }

    @FXML
    public void handleLogout(MouseEvent mouseEvent) throws IOException {
        Stage stage = (Stage) logout.getScene().getWindow(); // Cast this window to become a stage

        FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
        Parent root = loader.load();
        stage.setTitle("Student Management System - login ");
        System.out.println(LoginController.loggedUserUsername);
        stage.setScene(new Scene(root));
    }

    @FXML
    public void onProblemSubmit(MouseEvent mouseEvent) {
        String title = problemTitle.getText();
        String problemText = problem.getText();
        String id = studentId.getText();

        if (title.isEmpty() || problemText.isEmpty() || id.isEmpty()) {
            showAlert("Error", "All fields are required!");
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("student_problems.csv", true))) {
            writer.write(title + "," + problemText + "," + id);
            writer.newLine();
            clearFields();
            showAlert("Success", "Your problem has been submitted successfully.");
        } catch (IOException e) {
            showAlert("Error", "An error occurred while saving the problem.");
            e.printStackTrace();
        }
    }

    private void clearFields() {
        problemTitle.clear();
        problem.clear();
        studentId.clear();
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
