module de.lubowiecki.client {
    requires javafx.controls;
    requires javafx.fxml;

    opens de.lubowiecki.client to javafx.fxml;
    opens de.lubowiecki.client.controller to javafx.fxml;
    opens de.lubowiecki.client.model to javafx.fxml, javafx.base;
    exports de.lubowiecki.client;
}
