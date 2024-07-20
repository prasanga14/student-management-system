package com.example.sms.utils;

import com.example.sms.model.Staff;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StaffCSVUtils {

    private static final String CSV_FILE = "staffs_data.csv";

    // Check if CSV file exists
    private static boolean csvFileExists() {
        File file = new File(CSV_FILE);
        return file.exists();
    }

    // Initialize CSV file if it doesn't exist
    private static void initializeCSVFile() {
        if (!csvFileExists()) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(CSV_FILE))) {
                writer.write("staffId,staffName,position,faculty,shift,enrolledDate,status");
                writer.newLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Load students from CSV
    public static List<Staff> loadStaffs() throws IOException {
        initializeCSVFile(); // Ensure CSV file exists
        List<Staff> staffs = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(CSV_FILE))) {
            List<String[]> lines = reader.readAll();
            for (String[] data : lines) {
                if (data.length >= 7) {
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
            }
        }
        return staffs;
    }

    // Save students to CSV
    public static void saveStaffs(List<Staff> staffs) throws IOException {
        initializeCSVFile(); // Ensure CSV file exists
        try (CSVWriter writer = new CSVWriter(new FileWriter(CSV_FILE))) {
            for (Staff staff : staffs) {
                String[] data = {
                        staff.getStaffId(),
                        staff.getStaffName(),
                        staff.getPosition(),
                        staff.getFaculty(),
                        staff.getShift(),
                        staff.getEnrolledDate(),
                        staff.getStatus()
                };
                writer.writeNext(data);
            }
        }
    }

    // Delete a staff from CSV
    public static void deleteStaff(Staff staff) throws IOException {
        List<Staff> staffs = loadStaffs();
        staffs.removeIf(s -> s.getStaffId().equals(staff.getStaffId()));
        saveStaffs(staffs);
    }
}
