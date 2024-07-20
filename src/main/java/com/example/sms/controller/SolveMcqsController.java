package com.example.sms.controller;

import com.example.sms.model.MCQ;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SolveMcqsController {

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
    private Label questionLabel;
    @FXML
    private RadioButton option1;
    @FXML
    private RadioButton option2;
    @FXML
    private RadioButton option3;
    @FXML
    private RadioButton option4;
    @FXML
    private Button submitButton;

    private List<MCQ> mcqs;
    private int currentIndex = 0;
    private List<String> results;

    @FXML
    public void initialize() {
        loadQuestions();
        displayQuestion();
    }

    private void loadQuestions() {
        mcqs = new ArrayList<>();
        results = new ArrayList<>();
        String csvFile = "mcqs_records.csv"; // Update the path
        String line;
        String csvSplitBy = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                String[] data = line.split(csvSplitBy);
                String question = data[0];
                List<String> options = new ArrayList<>();
                options.add(data[1]);
                options.add(data[2]);
                options.add(data[3]);
                options.add(data[4]);
                String correctAnswer = data[5];
                mcqs.add(new MCQ(question, options, correctAnswer));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void displayQuestion() {
        if (mcqs == null || mcqs.isEmpty()) {
            questionLabel.setText("No questions available.");
            option1.setVisible(false);
            option2.setVisible(false);
            option3.setVisible(false);
            option4.setVisible(false);
            submitButton.setDisable(true);
            return;
        }

        MCQ currentMCQ = mcqs.get(currentIndex);
        questionLabel.setText(currentMCQ.getQuestion());

        List<String> options = currentMCQ.getOptions();
        option1.setText(options.get(0));
        option2.setText(options.get(1));
        option3.setText(options.get(2));
        option4.setText(options.get(3));
    }

    @FXML
    public void handleSubmit() {
        String selectedOption = "";
        if (option1.isSelected()) selectedOption = option1.getText();
        if (option2.isSelected()) selectedOption = option2.getText();
        if (option3.isSelected()) selectedOption = option3.getText();
        if (option4.isSelected()) selectedOption = option4.getText();

        if (!selectedOption.isEmpty()) {
            MCQ currentMCQ = mcqs.get(currentIndex);
            boolean isCorrect = currentMCQ.getCorrectAnswer().equals(selectedOption);

            results.add(currentMCQ.getQuestion() + "," + selectedOption + "," + (isCorrect ? "Correct" : "Wrong"));

            showAlert(isCorrect ? "Correct!" : "Wrong!");

            currentIndex++;
            if (currentIndex < mcqs.size()) {
                displayQuestion();
            } else {
                saveResults();
                questionLabel.setText("You've completed the quiz!");
                option1.setVisible(false);
                option2.setVisible(false);
                option3.setVisible(false);
                option4.setVisible(false);
                submitButton.setDisable(true);
            }
        }
    }

    private void saveResults() {
        String csvFile = "mcq_results.csv";
        try (FileWriter writer = new FileWriter(csvFile)) {
            for (String result : results) {
                writer.write(result + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showAlert(String message) {
        // Implement a method to show alerts or feedback to the user
        // For example, using JavaFX dialogs
        System.out.println(message); // Replace with actual dialog or alert
    }

    @FXML
    public void openViewReport(MouseEvent mouseEvent) throws IOException {
        Stage stage = (Stage) viewReportBtn.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("view-reports.fxml"));
        Parent root = loader.load();
        stage.setTitle("Student Management System - view-reports ");
        stage.setScene(new Scene(root));
    }

    @FXML
    public void openStudentProblemForm(MouseEvent mouseEvent) throws IOException {
        Stage stage = (Stage) studentProblemFormBtn.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("student-problem-form.fxml"));
        Parent root = loader.load();
        stage.setTitle("Student Management System - student-problem-form ");
        stage.setScene(new Scene(root));
    }

    @FXML
    public void openVisitLibrary(MouseEvent mouseEvent) throws IOException {
        Stage stage = (Stage) visitLibraryBtn.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("visit-library.fxml"));
        Parent root = loader.load();
        stage.setTitle("Student Management System - visit-library ");
        stage.setScene(new Scene(root));
    }

    @FXML
    public void openCounsellingForm(MouseEvent mouseEvent) throws IOException {
        Stage stage = (Stage) counsellingFormBtn.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("counselling-form.fxml"));
        Parent root = loader.load();
        stage.setTitle("Student Management System - counselling-form ");
        stage.setScene(new Scene(root));
    }

    @FXML
    public void openPlaySports(MouseEvent mouseEvent) throws IOException {
        Stage stage = (Stage) playSportsBtn.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("play-sports.fxml"));
        Parent root = loader.load();
        stage.setTitle("Student Management System - play-sports ");
        stage.setScene(new Scene(root));
    }

    @FXML
    public void openSolveMcqs(MouseEvent mouseEvent) throws IOException {
        // Already on the Solve MCQs screen
    }

    @FXML
    public void handleLogout(MouseEvent mouseEvent) throws IOException {
        Stage stage = (Stage) logout.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
        Parent root = loader.load();
        stage.setTitle("Student Management System - login ");
        stage.setScene(new Scene(root));
    }
}
