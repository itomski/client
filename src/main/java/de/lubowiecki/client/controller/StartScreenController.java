package de.lubowiecki.client.controller;

import de.lubowiecki.client.App;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StartScreenController implements Initializable {

    @FXML
    AnchorPane pane;

    class StartScreen extends Thread {

        @Override
        public void run() {
            try {
                Thread.sleep(5_000); // Wartet 10 Sekunden, bevor es wieter geht

                Platform.runLater(() -> {
                    try {
                        App.setRoot("controller/standard");
                    }
                    catch (IOException e) {
                        // TODO: Exception richtig behandeln
                        e.printStackTrace();
                    }
                });
            }
            catch (InterruptedException e) {
                // TODO: Exception richtig behandeln
                e.printStackTrace();
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        new StartScreen().start(); // <<< Hier muss start() statt run() rein!!!!
    }
}
