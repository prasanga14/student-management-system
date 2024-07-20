package com.example.sms.utils;

import com.example.sms.model.Student;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StudentCSVUtils {

    private static final String CSV_FILE = "students_data.csv";

    // Check if CSV file exists
    private static boolean csvFileExists() {
        File file = new File(CSV_FILE);
        return file.exists();
    }

    // Initialize CSV file if it doesn't exist
    private static void initializeCSVFile() {
        if (!csvFileExists()) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(CSV_FILE))) {
                writer.write("studentId,username,password,firstName,lastName,faculty,emailAddress,phoneNo,studentName,year,course,section,enrolledDate,status,gender");
                writer.newLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Load students from CSV
    public static List<Student> loadStudents() throws IOException {
        initializeCSVFile(); // Ensure CSV file exists
        List<Student> students = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(CSV_FILE))) {
            List<String[]> lines = reader.readAll();
            for (String[] data : lines) {
                if (data.length >= 15) {
                    Student student = new Student();
                    student.setStudentId(data[0]);
                    student.setUsername(data[1]);
                    student.setPassword(data[2]);
                    student.setFirstName(data[3]);
                    student.setLastName(data[4]);
                    student.setFaculty(data[5]);
                    student.setEmailAddress(data[6]);
                    student.setPhoneNo(data[7]);
                    student.setStudentName(data[8]);
                    student.setYear(data[9]);
                    student.setCourse(data[10]);
                    student.setSection(data[11]);
                    student.setEnrolledDate(data[12]);
                    student.setStatus(data[13]);
                    student.setGender(data[14]);
                    students.add(student);
                }
            }
        }
        return students;
    }

    // Save students to CSV
    public static void saveStudents(List<Student> students) throws IOException {
        initializeCSVFile(); // Ensure CSV file exists
        try (CSVWriter writer = new CSVWriter(new FileWriter(CSV_FILE))) {
            for (Student student : students) {
                String[] data = {
                        student.getStudentId(),
                        student.getUsername(),
                        student.getPassword(),
                        student.getFirstName(),
                        student.getLastName(),
                        student.getFaculty(),
                        student.getEmailAddress(),
                        student.getPhoneNo(),
                        student.getStudentName(),
                        student.getYear(),
                        student.getCourse(),
                        student.getSection(),
                        student.getEnrolledDate(),
                        student.getStatus(),
                        student.getGender()
                };
                writer.writeNext(data);
            }
        }
    }

    // Delete a student from CSV
    public static void deleteStudent(Student student) throws IOException {
        List<Student> students = loadStudents();
        students.removeIf(s -> s.getStudentId().equals(student.getStudentId()));
        saveStudents(students);
    }
}
