package com.example.sms;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class StudentDialogController {

    @FXML
    private TextField studentIdField;

    @FXML
    private TextField usernameField;

    @FXML
    private TextField passwordField;

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField facultyField;

    @FXML
    private TextField emailAddressField;

    @FXML
    private TextField phoneNoField;

    @FXML
    private TextField yearField;

    @FXML
    private TextField courseField;

    @FXML
    private TextField sectionField;

    @FXML
    private TextField enrolledDateField;

    @FXML
    private TextField statusField;

    @FXML
    private TextField genderField;

    private Stage dialogStage;
    private Student student;
    private boolean saveClicked = false;

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setStudent(Student student) {
        this.student = student;

        studentIdField.setText(student.getStudentId());
        // Set other fields here as needed
        usernameField.setText(student.getUsername());
        passwordField.setText(student.getPassword());
        firstNameField.setText(student.getFirstName());
        lastNameField.setText(student.getLastName());
        facultyField.setText(student.getFaculty());
        emailAddressField.setText(student.getEmailAddress());
        phoneNoField.setText(student.getPhoneNo());
        yearField.setText(student.getYear());
        courseField.setText(student.getCourse());
        sectionField.setText(student.getSection());
        enrolledDateField.setText(student.getEnrolledDate());
        statusField.setText(student.getStatus());
        genderField.setText(student.getGender());
    }

    public boolean isSaveClicked() {
        return saveClicked;
    }

    @FXML
    private void handleSaveAction() {
        // Update student object with fields
        student.setStudentId(studentIdField.getText());
        student.setUsername(usernameField.getText());
        student.setPassword(passwordField.getText());
        student.setFirstName(firstNameField.getText());
        student.setLastName(lastNameField.getText());
        student.setFaculty(facultyField.getText());
        student.setEmailAddress(emailAddressField.getText());
        student.setPhoneNo(phoneNoField.getText());
        student.setYear(yearField.getText());
        student.setCourse(courseField.getText());
        student.setSection(sectionField.getText());
        student.setEnrolledDate(enrolledDateField.getText());
        student.setStatus(statusField.getText());
        student.setGender(genderField.getText());

        saveClicked = true;
        dialogStage.close();
    }

    @FXML
    private void handleCancelAction() {
        dialogStage.close();
    }
}
