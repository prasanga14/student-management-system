module com.example.sms {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires opencsv;
    requires commons.logging;

    exports com.example.sms.utils;
    opens com.example.sms.utils to javafx.fxml;
    exports com.example.sms.mock;
    opens com.example.sms.mock to javafx.fxml;
    exports com.example.sms.model;
    opens com.example.sms.model to javafx.fxml;
    exports com.example.sms.controller;
    opens com.example.sms.controller to javafx.fxml;
}