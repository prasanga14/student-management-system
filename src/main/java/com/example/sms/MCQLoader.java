package com.example.sms;

import java.util.Arrays;
import java.util.List;

public class MCQLoader {

    public static List<MCQ> loadMCQs() {
        return Arrays.asList(
                new MCQ("What is the capital of France?",
                        Arrays.asList("Berlin", "Madrid", "Paris", "Rome"),
                        "Paris"),
                new MCQ("What is 2 + 2?",
                        Arrays.asList("3", "4", "5", "6"),
                        "4"),
                new MCQ("Who wrote 'To Kill a Mockingbird'?",
                        Arrays.asList("Harper Lee", "Mark Twain", "Ernest Hemingway", "J.K. Rowling"),
                        "Harper Lee")
        );
    }
}
