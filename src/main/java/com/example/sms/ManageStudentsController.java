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

public class ManageStudentsController {

    @FXML
    private TableView<Student> studentsTable;

    @FXML
    private TableColumn<Student, String> studentIdColumn;

    @FXML
    private TableColumn<Student, String> usernameColumn;

    @FXML
    private TableColumn<Student, String> passwordColumn;

    @FXML
    private TableColumn<Student, String> firstNameColumn;

    @FXML
    private TableColumn<Student, String> lastNameColumn;

    @FXML
    private TableColumn<Student, String> facultyColumn;

    @FXML
    private TableColumn<Student, String> emailAddressColumn;

    @FXML
    private TableColumn<Student, String> genderColumn;

    @FXML
    private TableColumn<Student, String> phoneNoColumn;

    @FXML
    private TableColumn<Student, String> yearColumn;

    @FXML
    private TableColumn<Student, String> courseColumn;

    @FXML
    private TableColumn<Student, String> sectionColumn;

    @FXML
    private TableColumn<Student, String> enrolledDateColumn;

    @FXML
    private TableColumn<Student, String> statusColumn;

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
    private Button logout;

    @FXML
    public void initialize() {
        // Initialize the table columns
        studentIdColumn.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        facultyColumn.setCellValueFactory(new PropertyValueFactory<>("faculty"));
        emailAddressColumn.setCellValueFactory(new PropertyValueFactory<>("emailAddress"));
        genderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));
        phoneNoColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNo"));
        yearColumn.setCellValueFactory(new PropertyValueFactory<>("year"));
        courseColumn.setCellValueFactory(new PropertyValueFactory<>("course"));
        sectionColumn.setCellValueFactory(new PropertyValueFactory<>("section"));
        enrolledDateColumn.setCellValueFactory(new PropertyValueFactory<>("enrolledDate"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        // Load student data into the table
        loadStudentData();
    }

    private void loadStudentData() {
        // Load data from CSV file and add to the table
        List<Student> students = CSVUtils.loadStudents();
        studentsTable.getItems().setAll(students);
    }

    @FXML
    private void handleAddAction() {
        Student newStudent = new Student();
        boolean saveClicked = showStudentDialog(newStudent);
        if (saveClicked) {
            studentsTable.getItems().add(newStudent);
            CSVUtils.saveStudents(studentsTable.getItems());
        }
    }

    @FXML
    private void handleUpdateAction() {
        Student selectedStudent = studentsTable.getSelectionModel().getSelectedItem();
        if (selectedStudent != null) {
            boolean saveClicked = showStudentDialog(selectedStudent);
            if (saveClicked) {
                CSVUtils.saveStudents(studentsTable.getItems());
            }
        } else {
            showAlert("No Selection", "No Student Selected", "Please select a student in the table.");
        }
    }

    @FXML
    private void handleDeleteAction() {
        // Get the selected student from the table
        Student selectedStudent = studentsTable.getSelectionModel().getSelectedItem();

        if (selectedStudent != null) {
            // Ask for confirmation before deleting
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm Delete");
            alert.setHeaderText("Deleting Student");
            alert.setContentText("Are you sure you want to delete the selected student?");

            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                // Delete the student from the table
                studentsTable.getItems().remove(selectedStudent);

                // Save the updated list of students to CSV
                CSVUtils.saveStudents(studentsTable.getItems());
            }
        } else {
            // If no student is selected, show an alert
            showAlert("No Selection", "No Student Selected", "Please select a student in the table.");
        }
    }

    @FXML
    private boolean showStudentDialog(Student student) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("student-dialog.fxml"));
            AnchorPane page = loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Student Details");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(studentsTable.getScene().getWindow());
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            StudentDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setStudent(student);

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
}
