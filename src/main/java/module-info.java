module de.lubowiecki.client {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens de.lubowiecki.client;
    opens de.lubowiecki.client.controller;
    opens de.lubowiecki.client.model;
    exports de.lubowiecki.client;
}
