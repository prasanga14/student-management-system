package com.example.sms.controller;

import com.example.sms.model.Staff;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class StaffDialogController {

    @FXML
    private TextField staffIdField;

    @FXML
    private TextField staffNameField;

    @FXML
    private TextField positionFeild;

    @FXML
    private TextField facultyFeild;

    @FXML
    private TextField shiftFeild;

    @FXML
    private TextField enrolledDateField;

    @FXML
    private TextField statusField;

    private Stage dialogStage;
    private Staff staff;
    private boolean saveClicked = false;

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;

        staffIdField.setText(staff.getStaffId());
        staffNameField.setText(staff.getStaffName());
        positionFeild.setText(staff.getPosition());
        facultyFeild.setText(staff.getFaculty());
        shiftFeild.setText(staff.getShift());
        enrolledDateField.setText(staff.getEnrolledDate());
        statusField.setText(staff.getStatus());
        System.out.println(staff.getStaffId());
        System.out.println(staff.getStaffName());
    }

    public boolean isSaveClicked() {
        return saveClicked;
    }

    @FXML
    private void handleSaveAction() {
        staff.setStaffId(staffIdField.getText());
        staff.setStaffName(staffNameField.getText());
        staff.setPosition(positionFeild.getText());
        staff.setFaculty(facultyFeild.getText());
        staff.setShift(shiftFeild.getText());
        staff.setEnrolledDate(enrolledDateField.getText());
        staff.setStatus(statusField.getText());

        saveClicked = true;
        dialogStage.close();
    }

    @FXML
    private void handleCancelAction() {
        dialogStage.close();
    }
}
