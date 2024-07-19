package com.example.sms;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;


public class AdminDashboardController {

    @FXML
    private Button manageStudentsBtn;

    @FXML
    private Button manageTeachersBtn;

    @FXML
    private Button adminDashboardLogout;

    @FXML
    private Button manageStaffsBtn;

    @FXML
    private Button openPaymentBtn;

    @FXML
    private Button studentReportNav;

    @FXML
    private Button viewResultsBtn;

    @FXML
    private Button createMcqsBtn;


    @FXML
    public void onAdminDashboardLogout(MouseEvent mouseEvent) throws IOException {
        Stage stage = (Stage) adminDashboardLogout.getScene().getWindow(); // Cast this window to become a stage

        FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
        Parent root = loader.load();
        stage.setTitle("Student Management System");
        stage.setScene(new Scene(root));
    }


    @FXML
    public void onManageStudentsBtn(MouseEvent mouseEvent) throws IOException{
        Stage stage = (Stage) manageStudentsBtn.getScene().getWindow(); // Cast this window to become a stage

        FXMLLoader loader = new FXMLLoader(getClass().getResource("manage-students.fxml"));
        Parent root = loader.load();
        stage.setTitle("Student Management System - manage students");
        stage.setScene(new Scene(root));
    }


    @FXML
    public void handleManageStaffs(MouseEvent mouseEvent) throws  IOException {
        System.out.println("Manage Staffs");
        Stage stage = (Stage) manageStaffsBtn.getScene().getWindow(); // Cast this window to become a stage

        FXMLLoader loader = new FXMLLoader(getClass().getResource("manage-staffs.fxml"));
        Parent root = loader.load();
        stage.setTitle("Student Management System - manage staffs");
        stage.setScene(new Scene(root));
    }

    public void openManageTeachers(MouseEvent mouseEvent) throws IOException {
        Stage stage = (Stage) manageTeachersBtn.getScene().getWindow(); // Cast this window to become a stage

        FXMLLoader loader = new FXMLLoader(getClass().getResource("manage-teachers.fxml"));
        Parent root = loader.load();
        stage.setTitle("Student Management System - manage teachers");
        stage.setScene(new Scene(root));
    }


    @FXML
    public void openPayments(MouseEvent mouseEvent) throws IOException {
        Stage stage = (Stage) openPaymentBtn.getScene().getWindow(); // Cast this window to become a stage

        FXMLLoader loader = new FXMLLoader(getClass().getResource("manage-payment.fxml"));
        Parent root = loader.load();
        stage.setTitle("Student Management System - manage payments");
        stage.setScene(new Scene(root));
    }

    @FXML
    public void openStudentReport(MouseEvent mouseEvent) throws  IOException {
        Stage stage = (Stage) studentReportNav.getScene().getWindow(); // Cast this window to become a stage

        FXMLLoader loader = new FXMLLoader(getClass().getResource("student-report.fxml"));
        Parent root = loader.load();
        stage.setTitle("Student Management System - student reports");
        stage.setScene(new Scene(root));
    }

    @FXML
    public void openViewResults(MouseEvent mouseEvent) throws  IOException {
        Stage stage = (Stage) viewResultsBtn.getScene().getWindow(); // Cast this window to become a stage

        FXMLLoader loader = new FXMLLoader(getClass().getResource("check-results.fxml"));
        Parent root = loader.load();
        stage.setTitle("Student Management System - view results");
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
