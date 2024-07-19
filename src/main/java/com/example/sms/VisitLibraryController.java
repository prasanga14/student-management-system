package com.example.sms;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.*;
import java.util.ResourceBundle;

public class VisitLibraryController {

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
    private static Label displayLoggedUser;

    @FXML
    private TextField borrowBookName;

    @FXML
    private DatePicker borrowDate;

    @FXML
    private DatePicker returnDate;

    @FXML
    private TextField availableBookName;

    @FXML
    private Label bookAvailabilityStatus;

    @FXML
    private Label bookingStatus;

    @FXML
    public void openViewReport(MouseEvent mouseEvent) throws IOException {
        Stage stage = (Stage) viewReportBtn.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("view-reports.fxml"));
        stage.setTitle("Student Management System - view-reports");
        stage.setScene(new Scene(root));
    }

    @FXML
    public void openStudentProblemForm(MouseEvent mouseEvent) throws IOException {
        Stage stage = (Stage) studentProblemFormBtn.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("student-problem-form.fxml"));
        stage.setTitle("Student Management System - student-problem-form");
        stage.setScene(new Scene(root));
    }

    @FXML
    public void openVisitLibrary(MouseEvent mouseEvent) throws IOException {
        Stage stage = (Stage) visitLibraryBtn.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("visit-library.fxml"));
        stage.setTitle("Student Management System - visit-library");
        stage.setScene(new Scene(root));
    }

    @FXML
    public void openCounsellingForm(MouseEvent mouseEvent) throws IOException {
        Stage stage = (Stage) counsellingFormBtn.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("counselling-form.fxml"));
        stage.setTitle("Student Management System - counselling-form");
        stage.setScene(new Scene(root));
    }

    @FXML
    public void openPlaySports(MouseEvent mouseEvent) throws IOException {
        Stage stage = (Stage) playSportsBtn.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("play-sports.fxml"));
        stage.setTitle("Student Management System - play-sports");
        stage.setScene(new Scene(root));
    }

    @FXML
    public void openSolveMcqs(MouseEvent mouseEvent) throws IOException {
        Stage stage = (Stage) solveMcqsBtn.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("solve-mcqs.fxml"));
        stage.setTitle("Student Management System - solve-mcqs");
        stage.setScene(new Scene(root));
    }

    @FXML
    public void handleLogout(MouseEvent mouseEvent) throws IOException {
        Stage stage = (Stage) logout.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        stage.setTitle("Student Management System - login");
        stage.setScene(new Scene(root));
    }

    @FXML
    public void onBookBtn(MouseEvent mouseEvent) {
        String bookName = borrowBookName.getText().trim();
        String borrowDateString = (borrowDate.getValue() != null) ? borrowDate.getValue().toString() : "";
        String returnDateString = (returnDate.getValue() != null) ? returnDate.getValue().toString() : "";

        if (bookName.isEmpty() || borrowDateString.isEmpty() || returnDateString.isEmpty()) {
            bookingStatus.setText("Please fill in all fields.");
            return;
        }

        if (saveBookingDetails(bookName, borrowDateString, returnDateString)) {
            bookingStatus.setText("Booking successful.");
            clearBookingFields();
        } else {
            bookingStatus.setText("Failed to book. Please try again.");
        }
    }

    private void clearBookingFields() {
        borrowBookName.clear();
        borrowDate.setValue(null);
        returnDate.setValue(null);
    }
    private void clearAvailableBookName() {
        availableBookName.clear();

    }

    @FXML
    public void onCheckBtn(MouseEvent mouseEvent) {
        String bookName = availableBookName.getText().trim();
        if (bookName.isEmpty()) {
            bookAvailabilityStatus.setText("Please enter a book name.");
            return;
        }

        boolean isAvailable = checkBookAvailability(bookName);

        if (isAvailable) {
            bookAvailabilityStatus.setText("available.");
            clearAvailableBookName();
        } else {
            bookAvailabilityStatus.setText("not available.");
            clearAvailableBookName();
        }
    }

    private boolean checkBookAvailability(String bookName) {
        String csvFile = "books.csv"; // Update with the correct path to your CSV file
        String line;
        String csvSplitBy = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                String[] book = line.split(csvSplitBy);
                if (book.length > 0 && book[0].equalsIgnoreCase(bookName)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    private boolean saveBookingDetails(String bookName, String borrowDate, String returnDate) {
        String csvFile = "bookings.csv"; // Update with the correct path to your CSV file

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(csvFile, true))) {
            String bookingDetails = String.format("%s,%s,%s%n", bookName, borrowDate, returnDate);
            bw.write(bookingDetails);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
