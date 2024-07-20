package com.example.sms.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class CreateMcqsController {

    @FXML
    private TextField questionField;

    @FXML
    private TextField optionAField;

    @FXML
    private TextField optionBField;

    @FXML
    private TextField optionCField;

    @FXML
    private TextField optionDField;

    @FXML
    private TextField correctAnswerField;

    @FXML
    private Button manageStudentBtn;

    @FXML
    private Button manageStaffs;

    @FXML
    private Button manageTeacherBtn;

    @FXML
    private Button managePaymentBtn;

    @FXML
    private Button studentReportBtn;

    @FXML
    private Button viewResultsBtn;

    @FXML
    private Button createMcqsBtn;

    @FXML
    private Button logout;

    @FXML
    private Button submitMcqBtn;

    @FXML
    public void onSubmitMcq(MouseEvent mouseEvent) {
        String question = questionField.getText();
        String optionA = optionAField.getText();
        String optionB = optionBField.getText();
        String optionC = optionCField.getText();
        String optionD = optionDField.getText();
        String correctAnswer = correctAnswerField.getText();

        // Validate input
        if (question.isEmpty() || optionA.isEmpty() || optionB.isEmpty() || optionC.isEmpty() || optionD.isEmpty() || correctAnswer.isEmpty()) {
            showAlert("Error", "Please fill in all fields.");
            return;
        }

        // Validate correct answer
        if (!"A".equals(correctAnswer) && !"B".equals(correctAnswer) && !"C".equals(correctAnswer) && !"D".equals(correctAnswer)) {
            showAlert("Error", "Correct answer must be A, B, C, or D.");
            return;
        }

        // Save to CSV file
        try (FileWriter fw = new FileWriter("mcqs_records.csv", true);
             PrintWriter pw = new PrintWriter(fw)) {
            pw.printf("%s,%s,%s,%s,%s,%s%n", question, optionA, optionB, optionC, optionD, correctAnswer);
            showAlert("Success", "MCQ saved successfully.");
            clearFields();
        } catch (IOException e) {
            showAlert("Error", "Failed to save MCQ.");
            e.printStackTrace();
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void clearFields() {
        questionField.clear();
        optionAField.clear();
        optionBField.clear();
        optionCField.clear();
        optionDField.clear();
        correctAnswerField.clear();
    }

    @FXML
    public void openManageStaffs(MouseEvent mouseEvent) throws IOException {
        Stage stage = (Stage) manageStaffs.getScene().getWindow(); // Cast this window to become a stage

        FXMLLoader loader = new FXMLLoader(getClass().getResource("manage-staffs.fxml"));
        Parent root = loader.load();
        stage.setTitle("Student Management System - manage staffs");
        stage.setScene(new Scene(root));
    }

    @FXML
    public void openManageTeacher(MouseEvent mouseEvent) throws IOException {
        Stage stage = (Stage) manageTeacherBtn.getScene().getWindow(); // Cast this window to become a stage

        FXMLLoader loader = new FXMLLoader(getClass().getResource("manage-teachers.fxml"));
        Parent root = loader.load();
        stage.setTitle("Student Management System - manage teachers");
        stage.setScene(new Scene(root));
    }

    @FXML
    public void openManageStudent(MouseEvent mouseEvent) throws IOException {
        Stage stage = (Stage) manageStudentBtn.getScene().getWindow(); // Cast this window to become a stage

        FXMLLoader loader = new FXMLLoader(getClass().getResource("manage-students.fxml"));
        Parent root = loader.load();
        stage.setTitle("Student Management System - manage students");
        stage.setScene(new Scene(root));
    }

    @FXML
    public void openPayment(MouseEvent mouseEvent) throws IOException {
        Stage stage = (Stage) managePaymentBtn.getScene().getWindow(); // Cast this window to become a stage

        FXMLLoader loader = new FXMLLoader(getClass().getResource("manage-payment.fxml"));
        Parent root = loader.load();
        stage.setTitle("Student Management System - manage payments");
        stage.setScene(new Scene(root));
    }

    @FXML
    public void openStudentReport(MouseEvent mouseEvent) throws IOException {
        Stage stage = (Stage) studentReportBtn.getScene().getWindow(); // Cast this window to become a stage

        FXMLLoader loader = new FXMLLoader(getClass().getResource("student-report.fxml"));
        Parent root = loader.load();
        stage.setTitle("Student Management System - manage student reports");
        stage.setScene(new Scene(root));
    }

    @FXML
    public void openResults(MouseEvent mouseEvent) throws IOException {
        Stage stage = (Stage) viewResultsBtn.getScene().getWindow(); // Cast this window to become a stage

        FXMLLoader loader = new FXMLLoader(getClass().getResource("check-results.fxml"));
        Parent root = loader.load();
        stage.setTitle("Student Management System - check student results");
        stage.setScene(new Scene(root));
    }

    @FXML
    public void handleLogout() throws IOException {
        Stage stage = (Stage) logout.getScene().getWindow(); // Cast this window to become a stage

        FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
        Parent root = loader.load();
        stage.setTitle("Student Management System");
        stage.setScene(new Scene(root));
    }

    @FXML
    public void openCreateMcqs(MouseEvent mouseEvent) throws IOException {
        Stage stage = (Stage) createMcqsBtn.getScene().getWindow(); // Cast this window to become a stage

        FXMLLoader loader = new FXMLLoader(getClass().getResource("create-mcqs.fxml"));
        Parent root = loader.load();
        stage.setTitle("Student Management System - create MCQs");
        stage.setScene(new Scene(root));
    }
}
