package com.example.sms.controller;

import com.example.sms.model.Staff;
import com.example.sms.utils.CSVUtils;
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

public class ManageStaffsController {

    @FXML
    private TableView<Staff> staffsTable;

    @FXML
    private TableColumn<Staff, String> staffIdColumn;

    @FXML
    private TableColumn<Staff, String> staffNameColumn;

    @FXML
    private TableColumn<Staff, String> positionColumn;

    @FXML
    private TableColumn<Staff, String> facultyColumn;

    @FXML
    private TableColumn<Staff, String> shiftColumn;

    @FXML
    private TableColumn<Staff, String> enrolledDateColumn;

    @FXML
    private TableColumn<Staff, String> statusColumn;

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
        staffIdColumn.setCellValueFactory(new PropertyValueFactory<>("staffId"));
        staffNameColumn.setCellValueFactory(new PropertyValueFactory<>("staffName"));
        positionColumn.setCellValueFactory(new PropertyValueFactory<>("position"));
        facultyColumn.setCellValueFactory(new PropertyValueFactory<>("faculty"));
        shiftColumn.setCellValueFactory(new PropertyValueFactory<>("shift"));
        enrolledDateColumn.setCellValueFactory(new PropertyValueFactory<>("enrolledDate"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        // Load staff data into the table
        loadStaffData();
    }



    @FXML
    private void loadStaffData() {
        // Load data from CSV file and add to the table
        List<Staff> staffs = CSVUtils.loadStaffs();
        staffsTable.getItems().setAll(staffs);
    }

    @FXML
    private void handleAddAction() {
        Staff newStaff = new Staff();
        boolean saveClicked = showStaffDialog(newStaff);
        if (saveClicked) {
            staffsTable.getItems().add(newStaff);
            CSVUtils.saveStaffs(staffsTable.getItems());
        }
    }

    @FXML
    private void handleUpdateAction() {
        Staff selectedStaff = staffsTable.getSelectionModel().getSelectedItem();
        if (selectedStaff != null) {
            boolean saveClicked = showStaffDialog(selectedStaff);
            if (saveClicked) {
                CSVUtils.saveStaffs(staffsTable.getItems());
            }
        } else {
            showAlert("No Selection", "No Staff Selected", "Please select a Staff in the table.");
        }
    }

    @FXML
    private void handleDeleteAction() {
        // Get the selected Staff from the table
        Staff selectedStaff = staffsTable.getSelectionModel().getSelectedItem();

        if (staffsTable != null) {
            // Ask for confirmation before deleting
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm Delete");
            alert.setHeaderText("Deleting Staff");
            alert.setContentText("Are you sure you want to delete the selected Staff?");

            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                // Delete the staff from the table
                staffsTable.getItems().remove(selectedStaff);

                // Save the updated list of staff to CSV
                CSVUtils.saveStaffs(staffsTable.getItems());
            }
        } else {
            // If no staff is selected, show an alert
            showAlert("No Selection", "No staff Selected", "Please select a staff in the table.");
        }
    }

    @FXML
    private boolean showStaffDialog(Staff staff) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("staff-dialog.fxml"));
            AnchorPane page = loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Staff Details");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(staffsTable.getScene().getWindow());
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            StaffDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setStaff(staff);

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
