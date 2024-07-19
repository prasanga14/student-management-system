package com.example.sms;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class RegisterController {

    @FXML
    private TextField registerUsername;

    @FXML
    private TextField registerEmail;

    @FXML
    private PasswordField registerPassword;

    @FXML
    private PasswordField confirmPassword;

    @FXML
    private Button registerPageLogin;

    @FXML
    private Label errorMessage;

    @FXML
    public void onRegisterBtnClicked(MouseEvent actionEvent) {
        String username = registerUsername.getText();
        String email = registerEmail.getText();
        String password = registerPassword.getText();
        String confirmedPassword = confirmPassword.getText();

        if (username.isEmpty() || email.isEmpty() || password.isEmpty() || confirmedPassword.isEmpty()) {
            errorMessage.setText("Please fill in all fields.");
            return;
        }

        if (!isValidEmail(email)) {
            errorMessage.setText("Invalid email format.");
            return;
        }

        if (CSVUtils.userExists(username)) {
            errorMessage.setText("Username already exists.");
            return;
        }

        if (!password.equals(confirmedPassword)) {
            errorMessage.setText("Passwords do not match.");
            return;
        }

        // Generate salt and hash password
        String salt = EncryptPassword.generateSalt();
        String hashedPassword = EncryptPassword.hashPassword(password, salt);

        // Save username, salt, hashed password, and email to CSV
        CSVUtils.saveUserData(username, salt, hashedPassword, email);

        // Clear fields and show success message (optional)
        registerUsername.clear();
        registerEmail.clear();
        registerPassword.clear();
        confirmPassword.clear();
        errorMessage.setText("Registration successful.");
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email.matches(emailRegex);
    }

    public void onRegisterLoginClicked(MouseEvent mouseEvent) throws IOException {
        Stage stage = (Stage) registerPageLogin.getScene().getWindow(); // Cast this window to become a stage

        FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
        Parent root = loader.load();
        stage.setTitle("Student Management System");
        stage.setScene(new Scene(root));
    }
}
