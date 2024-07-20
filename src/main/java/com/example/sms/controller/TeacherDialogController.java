package com.example.sms.controller;

import com.example.sms.model.Teacher;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class TeacherDialogController {

    @FXML
    private TextField teacherIdField;

    @FXML
    private TextField teacherNameField;

    @FXML
    private TextField teacherTypeField;

    @FXML
    private TextField teacherFacultyField;

    @FXML
    private TextField teacherSubjectField;

    @FXML
    private TextField enrolledDateField;

    @FXML
    private TextField statusField;

    private Stage dialogStage;
    private Teacher teacher;
    private boolean saveClicked = false;

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;

        teacherIdField.setText(teacher.getTeacherId());
        teacherNameField.setText(teacher.getTeacherName());
        teacherTypeField.setText(teacher.getType());
        teacherFacultyField.setText(teacher.getFaculty());
        teacherSubjectField.setText(teacher.getSubject());
        enrolledDateField.setText(teacher.getEnrolledDate());
        statusField.setText(teacher.getStatus());
        System.out.println(teacher.getTeacherId());
        System.out.println(teacher.getTeacherName());
    }

    public boolean isSaveClicked() {
        return saveClicked;
    }

    @FXML
    private void handleSaveAction() {
        teacher.setTeacherId(teacherIdField.getText());
        teacher.setTeacherName(teacherNameField.getText());
        teacher.setType(teacherTypeField.getText());
        teacher.setFaculty(teacherFacultyField.getText());
        teacher.setSubject(teacherSubjectField.getText());
        teacher.setEnrolledDate(enrolledDateField.getText());
        teacher.setStatus(statusField.getText());

        saveClicked = true;
        dialogStage.close();
    }

    @FXML
    private void handleCancelAction() {
        dialogStage.close();
    }
}
