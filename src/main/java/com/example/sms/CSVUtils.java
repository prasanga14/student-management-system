package com.example.sms;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import javafx.collections.ObservableList;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CSVUtils {

    private static final String CSV_FILE = "user_data.csv";
    private static final String STUDENTS_CSV_FILE_PATH = "students.csv";
    private static final String STAFFS_CSV_FILE_PATH = "staffs.csv";
    private static final String TEACHERS_CSV_FILE_PATH = "teachers.csv";
    private static final String PAYMENTS_CSV_FILE_PATH = "payments.csv";
    private static final String REPORTS_CSV_FILE_PATH = "reports.csv";

    // Check if CSV file exists
    private static boolean csvFileExists() {
        File file = new File(CSV_FILE);
        return file.exists();
    }

    // Initialize CSV file if it doesn't exist
    private static void initializeCSVFile() {
        if (!csvFileExists()) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(CSV_FILE))) {
                writer.write("username,email,salt,password");
                writer.newLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Check if username already exists in CSV
    public static boolean userExists(String username) {
        initializeCSVFile(); // Ensure CSV file exists
        try (CSVReader reader = new CSVReader(new FileReader(CSV_FILE))) {
            List<String[]> lines = reader.readAll();
            for (String[] data : lines) {
                if (data.length >= 4 && data[0].equals(username)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Validate user credentials during login
    public static boolean validateUser(String username, String password) {
        initializeCSVFile(); // Ensure CSV file exists
        try (CSVReader reader = new CSVReader(new FileReader(CSV_FILE))) {
            List<String[]> lines = reader.readAll();
            for (String[] data : lines) {
                if (data.length >= 4 && data[0].equals(username)) {
                    String storedSalt = data[2];
                    String storedPasswordHash = data[3];
                    String hashedPassword = EncryptPassword.hashPassword(password, storedSalt);
                    return hashedPassword.equals(storedPasswordHash);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Save user data (username, email, salt, hashed password) to CSV
    public static void saveUserData(String username, String salt, String hashedPassword, String email) {
        initializeCSVFile(); // Ensure CSV file exists
        try (CSVWriter writer = new CSVWriter(new FileWriter(CSV_FILE, true))) {
            String[] data = {username, email, salt, hashedPassword};
            writer.writeNext(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Student> loadStudents() {
        List<Student> students = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(STUDENTS_CSV_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                Student student = new Student();
                student.setStudentId(data[0]);
                student.setUsername(data[1]);
                student.setPassword(data[2]);
                student.setFirstName(data[3]);
                student.setLastName(data[4]);
                student.setFaculty(data[5]);
                student.setEmailAddress(data[6]);
                student.setGender(data[7]);
                student.setPhoneNo(data[8]);
                student.setStudentName(data[9]);
                student.setYear(data[10]);
                student.setCourse(data[11]);
                student.setSection(data[12]);
                student.setEnrolledDate(data[13]);
                student.setStatus(data[14]);
                students.add(student);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return students;
    }

    public static void saveStudents(ObservableList<Student> students) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(STUDENTS_CSV_FILE_PATH))) {
            for (Student student : students) {
                writer.write(String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s",
                        student.getStudentId(), student.getUsername(), student.getPassword(), student.getFirstName(),
                        student.getLastName(), student.getFaculty(), student.getEmailAddress(), student.getGender(),
                        student.getPhoneNo(), student.getStudentName(), student.getYear(), student.getCourse(),
                        student.getSection(), student.getEnrolledDate(), student.getStatus()));
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Staff> loadStaffs() {
        List<Staff> staffs = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(STAFFS_CSV_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                Staff staff = new Staff();
                staff.setStaffId(data[0]);
                staff.setStaffName(data[1]);
                staff.setPosition(data[2]);
                staff.setFaculty(data[3]);
                staff.setShift(data[4]);
                staff.setEnrolledDate(data[5]);
                staff.setStatus(data[6]);
                staffs.add(staff);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return staffs;
    }

    public static void saveStaffs(ObservableList<Staff> staffs) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(STAFFS_CSV_FILE_PATH))) {
            for (Staff staff : staffs) {
                writer.write(String.format("%s,%s,%s,%s,%s,%s,%s",
                        staff.getStaffId(), staff.getStaffName(), staff.getPosition(),
                        staff.getFaculty(), staff.getShift(), staff.getEnrolledDate(),
                        staff.getStatus()));
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Teacher> loadTeachers() {
        List<Teacher> teachers = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(TEACHERS_CSV_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                Teacher teacher = new Teacher();
                teacher.setTeacherId(data[0]);
                teacher.setTeacherName(data[1]);
                teacher.setType(data[2]);
                teacher.setFaculty(data[3]);
                teacher.setSubject(data[4]);
                teacher.setEnrolledDate(data[5]);
                teacher.setStatus(data[6]);
                teachers.add(teacher);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return teachers;
    }

    public static void saveTeachers(ObservableList<Teacher> teachers) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(TEACHERS_CSV_FILE_PATH))) {
            for (Teacher teacher : teachers) {
                writer.write(String.format("%s,%s,%s,%s,%s,%s,%s",
                        teacher.getTeacherId(), teacher.getTeacherName(), teacher.getType(),
                        teacher.getFaculty(), teacher.getSubject(), teacher.getEnrolledDate(),
                        teacher.getStatus()));
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Payment> loadPayments() {
        List<Payment> payments = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(PAYMENTS_CSV_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                Payment payment = new Payment();
                payment.setPaymentId(data[0]);
                payment.setStudentId(data[1]);
                payment.setStudentName(data[2]);
                payment.setFaculty(data[3]);
                payment.setDue(data[4]);
                payment.setDeadline(data[5]);
                payment.setStatus(data[6]);
                payments.add(payment);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return payments;
    }

    public static void savePayments(ObservableList<Payment> payments) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PAYMENTS_CSV_FILE_PATH))) {
            for (Payment payment : payments) {
                writer.write(String.format("%s,%s,%s,%s,%s,%s,%s",
                        payment.getPaymentId(), payment.getStudentId(), payment.getStudentName(),
                        payment.getFaculty(), payment.getDue(), payment.getDeadline(),
                        payment.getStatus()));
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
