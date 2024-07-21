package com.example.sms.controller;

import com.example.sms.utils.DataSingleton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StudentReportController {

    @FXML
    private TextField studentIdField;

    @FXML
    private TextField studentNameField;

    @FXML
    private TextField reportTitleField;

    @FXML
    private TextArea reportDescriptionField;

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
    public void onSentReport(MouseEvent mouseEvent) {
        String studentId = studentIdField.getText();
        String studentName = studentNameField.getText();
        String reportTitle = reportTitleField.getText();
        String reportDescription = reportDescriptionField.getText();

        // Define the file path for the CSV file
        File file = new File("student_reports.csv");

        // Save the report to the CSV file
        boolean isSaved = saveReportToCSV(file, studentId, studentName, reportTitle, reportDescription);

        // Clear the input fields if the report is saved successfully
        if (isSaved) {
            studentIdField.clear();
            studentNameField.clear();
            reportTitleField.clear();
            reportDescriptionField.clear();
        }
    }

    private boolean saveReportToCSV(File file, String studentId, String studentName, String reportTitle, String reportDescription) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) { // 'true' for appending to the file
            // Check if the file is empty to write headers
            if (file.length() == 0) {
                writer.write("Student ID,Student Name,Report Title,Report Description");
                writer.newLine();
            }

            // Write data
            writer.write(studentId + "," + studentName + "," + reportTitle + "," + reportDescription);
            writer.newLine();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
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
    public void openCreateMcqs(MouseEvent mouseEvent) throws IOException {
        Stage stage = (Stage) createMcqsBtn.getScene().getWindow(); // Cast this window to become a stage

        FXMLLoader loader = new FXMLLoader(getClass().getResource("create-mcqs.fxml"));
        Parent root = loader.load();
        stage.setTitle("Student Management System - create MCQs");
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
}
