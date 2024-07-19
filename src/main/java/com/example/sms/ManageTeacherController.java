package com.example.sms;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class ManageTeacherController {

    @FXML
    private TableView<Teacher> teachersTable;

    @FXML
    private TableColumn<Teacher, String> teacherIdColumn;

    @FXML
    private TableColumn<Teacher, String> teacherNameColumn;

    @FXML
    private TableColumn<Teacher, String> teacherTypeColumn;

    @FXML
    private TableColumn<Teacher, String> teacherFacultyColumn;

    @FXML
    private TableColumn<Teacher, String> teacherSubjectColumn;

    @FXML
    private TableColumn<Teacher, String> enrolledDateColumn;

    @FXML
    private TableColumn<Teacher, String> statusColumn;

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
    public void initialize() {
        // Initialize the table columns
        teacherIdColumn.setCellValueFactory(new PropertyValueFactory<>("teacherId"));
        teacherNameColumn.setCellValueFactory(new PropertyValueFactory<>("teacherName"));
        teacherTypeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        teacherFacultyColumn.setCellValueFactory(new PropertyValueFactory<>("faculty"));
        teacherSubjectColumn.setCellValueFactory(new PropertyValueFactory<>("subject"));
        enrolledDateColumn.setCellValueFactory(new PropertyValueFactory<>("enrolledDate"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        // Load staff data into the table
        loadTeacherData();
    }



    @FXML
    private void loadTeacherData() {
        // Load data from CSV file and add to the table
        List<Teacher> teachers = CSVUtils.loadTeachers();
        teachersTable.getItems().setAll(teachers);
    }

    @FXML
    private void handleAddAction() {
        Teacher newTeacher = new Teacher();
        boolean saveClicked = showTeacherDialog(newTeacher);
        if (saveClicked) {
            teachersTable.getItems().add(newTeacher);
            CSVUtils.saveTeachers(teachersTable.getItems());
        }
    }

    @FXML
    private void handleUpdateAction() {
        Teacher selectedTeacher = teachersTable.getSelectionModel().getSelectedItem();
        if (selectedTeacher != null) {
            boolean saveClicked = showTeacherDialog(selectedTeacher);
            if (saveClicked) {
                CSVUtils.saveTeachers(teachersTable.getItems());
            }
        } else {
            showAlert("No Selection", "No Staff Selected", "Please select a Staff in the table.");
        }
    }

    @FXML
    private void handleDeleteAction() {
        // Get the selected Staff from the table
        Teacher selectedTeacher = teachersTable.getSelectionModel().getSelectedItem();

        if (teachersTable != null) {
            // Ask for confirmation before deleting
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm Delete");
            alert.setHeaderText("Deleting Teacher");
            alert.setContentText("Are you sure you want to delete the selected Teacher?");

            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                // Delete the teacher from the table
                teachersTable.getItems().remove(selectedTeacher);

                // Save the updated list of staff to CSV
                CSVUtils.saveTeachers(teachersTable.getItems());
            }
        } else {
            // If no staff is selected, show an alert
            showAlert("No Selection", "No Teacher Selected", "Please select a Teacher in the table.");
        }
    }

    @FXML
    private boolean showTeacherDialog(Teacher teacher) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("teacher-dialog.fxml"));
            AnchorPane page = loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Teachers Details");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(teachersTable.getScene().getWindow());
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            TeacherDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setTeacher(teacher);

            dialogStage.showAndWait();

            return controller.isSaveClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @FXML
    private void showAlert(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

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
    public void handleLogout () throws IOException{
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
