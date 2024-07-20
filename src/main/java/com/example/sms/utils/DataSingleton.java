package com.example.sms.utils;

public class DataSingleton {
    private static final DataSingleton instance = new DataSingleton();

    private String userName;

    public DataSingleton(String userName) {
        this.userName = userName;
    }

    public DataSingleton() {
    }

    public static DataSingleton getInstance() {
        return instance;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}