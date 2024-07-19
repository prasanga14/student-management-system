package com.example.sms;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CheckResultsController {

    @FXML
    private TextField studentIdField;


    @FXML
    private TextArea resultsTextArea;

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
    private Button checkBtn;

    @FXML
    public void openManageStudent(MouseEvent mouseEvent) throws  IOException {
        Stage stage = (Stage) manageStudentBtn.getScene().getWindow(); // Cast this window to become a stage

        FXMLLoader loader = new FXMLLoader(getClass().getResource("manage-students.fxml"));
        Parent root = loader.load();
        stage.setTitle("Student Management System - manage students");
        stage.setScene(new Scene(root));

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
    public void openManageTeacher(MouseEvent mouseEvent) throws  IOException {
        Stage stage = (Stage) manageTeacherBtn.getScene().getWindow(); // Cast this window to become a stage

        FXMLLoader loader = new FXMLLoader(getClass().getResource("manage-teachers.fxml"));
        Parent root = loader.load();
        stage.setTitle("Student Management System - manage teachers");
        stage.setScene(new Scene(root));

    }


    @FXML
    public void openPayment(MouseEvent mouseEvent) throws  IOException {
        Stage stage = (Stage) managePaymentBtn.getScene().getWindow(); // Cast this window to become a stage

        FXMLLoader loader = new FXMLLoader(getClass().getResource("manage-payment.fxml"));
        Parent root = loader.load();
        stage.setTitle("Student Management System - manage payments");
        stage.setScene(new Scene(root));

    }

    @FXML
    public void openStudentReport(MouseEvent mouseEvent) throws  IOException {
        Stage stage = (Stage) studentReportBtn.getScene().getWindow(); // Cast this window to become a stage

        FXMLLoader loader = new FXMLLoader(getClass().getResource("student-report.fxml"));
        Parent root = loader.load();
        stage.setTitle("Student Management System - manage student reports");
        stage.setScene(new Scene(root));

    }

    @FXML
    public void openResults(MouseEvent mouseEvent) throws  IOException {
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
    public void handleLogout () throws IOException{
        Stage stage = (Stage) logout.getScene().getWindow(); // Cast this window to become a stage

        FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
        Parent root = loader.load();
        stage.setTitle("Student Management System");
        stage.setScene(new Scene(root));
    }

    @FXML
    public void displayResults(MouseEvent mouseEvent) {
        String studentId = studentIdField.getText().trim();
        if (studentId.isEmpty()) {
            resultsTextArea.setText("Please enter a student ID.");
            return;
        }

        String studentName = "";
        List<String[]> studentResults = new ArrayList<>();

        try (BufferedReader studentReader = new BufferedReader(new FileReader("students.csv"));
             BufferedReader resultReader = new BufferedReader(new FileReader("student_results.csv"))) {

            String line;

            // Find student details
            boolean studentFound = false;

            while ((line = studentReader.readLine()) != null) {
                String[] data = line.split(",");
                System.out.println(data[0]);
                if (data.length >= 2 && data[0].trim().equals(studentId)) {
                    studentName = data[1] + " " + data[2]; // Assuming first name and last name are in separate columns
                    studentFound = true;
                    break;
                }
            }

            if (!studentFound) {
                resultsTextArea.setText("Student not found.");
                return;
            }

            // Find student results
            while ((line = resultReader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 3 && data[0].trim().equals(studentId)) {
                    studentResults.add(data);
                }
            }

            if (studentResults.isEmpty()) {
                resultsTextArea.setText("No results found for student ID: " + studentId);
                return;
            }

            // Constructing the result text
            StringBuilder resultText = new StringBuilder();
            resultText.append("Results for Student ID: ").append(studentId).append("\n");
            resultText.append("Student Name: ").append(studentName).append("\n\n");

            for (String[] result : studentResults) {
                resultText.append("Subject: ").append(result[1]).append("\n");
                resultText.append("Grade: ").append(result[2]).append("\n\n");
            }

            // Set the text in TextArea
            resultsTextArea.setText(resultText.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}