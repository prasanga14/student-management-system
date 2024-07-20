package com.example.sms.controller;

import com.example.sms.model.Payment;
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

public class PaymentController {

    @FXML
    private TableView<Payment> paymentsTable;

    @FXML
    private TableColumn<Payment, String> paymentIdColumn;

    @FXML
    private TableColumn<Payment, String> studentIdColumn;

    @FXML
    private TableColumn<Payment, String> studentNameColumn;

    @FXML
    private TableColumn<Payment, String> facultyColumn;

    @FXML
    private TableColumn<Payment, String> dueColumn;

    @FXML
    private TableColumn<Payment, String> deadlineColumn;

    @FXML
    private TableColumn<Payment, String> statusColumn;

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
        paymentIdColumn.setCellValueFactory(new PropertyValueFactory<>("paymentId"));
        studentIdColumn.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        studentNameColumn.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        facultyColumn.setCellValueFactory(new PropertyValueFactory<>("faculty"));
        dueColumn.setCellValueFactory(new PropertyValueFactory<>("due"));
        deadlineColumn.setCellValueFactory(new PropertyValueFactory<>("deadline"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        // Load staff data into the table
        loadPaymentData();
    }



    @FXML
    private void loadPaymentData() {
        // Load data from CSV file and add to the table
        List<Payment> payments = CSVUtils.loadPayments();
        paymentsTable.getItems().setAll(payments);
    }

    @FXML
    private void handleAddAction() {
        Payment newPayment = new Payment();
        boolean saveClicked = showPaymentDialog(newPayment);
        if (saveClicked) {
            paymentsTable.getItems().add(newPayment);
            CSVUtils.savePayments(paymentsTable.getItems());
        }
    }

    @FXML
    private void handleUpdateAction() {
        Payment selectedPayment = paymentsTable.getSelectionModel().getSelectedItem();
        if (selectedPayment != null) {
            boolean saveClicked = showPaymentDialog(selectedPayment);
            if (saveClicked) {
                CSVUtils.savePayments(paymentsTable.getItems());
            }
        } else {
            showAlert("No Selection", "No Payment Selected", "Please select a Payment in the table.");
        }
    }

    @FXML
    private void handleDeleteAction() {
        // Get the selected Staff from the table
        Payment selectedPayment = paymentsTable.getSelectionModel().getSelectedItem();

        if (paymentsTable != null) {
            // Ask for confirmation before deleting
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm Delete");
            alert.setHeaderText("Deleting Payment");
            alert.setContentText("Are you sure you want to delete the selected Payment?");

            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                // Delete the teacher from the table
                paymentsTable.getItems().remove(selectedPayment);

                // Save the updated list of staff to CSV
                CSVUtils.savePayments(paymentsTable.getItems());
            }
        } else {
            // If no staff is selected, show an alert
            showAlert("No Selection", "No Payment Selected", "Please select a Payment in the table.");
        }
    }

    @FXML
    private boolean showPaymentDialog(Payment payment) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("payment-dialog.fxml"));
            AnchorPane page = loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Payment Details");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(paymentsTable.getScene().getWindow());
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            PaymentDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setPayment(payment);

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


}
