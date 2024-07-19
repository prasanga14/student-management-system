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

public class Login {

    @FXML
    private Button loginBtn;

    @FXML
    private Button openLoginScene;

    @FXML
    private TextField loginUsername;

    @FXML
    private PasswordField loginPassword;

    public static String loggedUserUsername;

    @FXML
    private Label errorMessage; // Added errorMessage label


    // Handle login button click event
    @FXML
    public void onLoginBtnClicked(MouseEvent mouseEvent) throws IOException, NullPointerException {
        String username = loginUsername.getText();
        String password = loginPassword.getText();

        if (username != null && !username.isEmpty() && password != null && !password.isEmpty()) {
            if (CSVUtils.validateUser(username, password)) {
                Stage stage = (Stage) loginBtn.getScene().getWindow(); // Cast this window to become a stage
                FXMLLoader loader;

                if (username.equals("admin")) {
                    loader = new FXMLLoader(getClass().getResource("admin-dashboard.fxml"));
                } else {
                    loader = new FXMLLoader(getClass().getResource("student-dashboard.fxml"));
                    loggedUserUsername = username;
                }
                Parent root = loader.load();
                stage.setTitle("Student Management System");
                stage.setScene(new Scene(root));

            } else {
                errorMessage.setText("Invalid username or password.");
            }
        } else {
            errorMessage.setText("Please enter username and password.");
        }
    }

    // Handle switching to registration scene
    @FXML
    public void onRegisterLoginScene(MouseEvent actionEvent) throws IOException {
        Stage stage = (Stage) openLoginScene.getScene().getWindow(); // Cast this window to become a stage

        FXMLLoader loader = new FXMLLoader(getClass().getResource("register.fxml"));
        Parent root = loader.load();
        stage.setTitle("Student Management System");
        stage.setScene(new Scene(root));
    }
}
