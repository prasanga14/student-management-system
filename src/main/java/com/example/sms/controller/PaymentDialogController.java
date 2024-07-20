package com.example.sms.controller;

import com.example.sms.model.Payment;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PaymentDialogController {

    @FXML
    private TextField paymentIdField;

    @FXML
    private TextField studentIdField;

    @FXML
    private TextField studentNameField;

    @FXML
    private TextField facultyField;

    @FXML
    private TextField dueField;

    @FXML
    private TextField deadlineField;

    @FXML
    private TextField statusField;

    private Stage dialogStage;
    private Payment payment;
    private boolean saveClicked = false;

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;

        paymentIdField.setText(payment.getPaymentId());
        studentIdField.setText(payment.getStudentId());
        studentNameField.setText(payment.getStudentName());
        facultyField.setText(payment.getFaculty());
        dueField.setText(payment.getDue());
        deadlineField.setText(payment.getDeadline());
        statusField.setText(payment.getStatus());
        System.out.println(payment.getPaymentId());
        System.out.println(payment.getStudentName());
    }

    public boolean isSaveClicked() {
        return saveClicked;
    }

    @FXML
    private void handleSaveAction() {
        payment.setPaymentId(paymentIdField.getText());
        payment.setStudentId(studentIdField.getText());
        payment.setStudentName(studentNameField.getText());
        payment.setFaculty(facultyField.getText());
        payment.setDue(dueField.getText());
        payment.setDeadline(deadlineField.getText());
        payment.setStatus(statusField.getText());

        saveClicked = true;
        dialogStage.close();
    }

    @FXML
    private void handleCancelAction() {
        dialogStage.close();
    }
}
