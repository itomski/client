package de.lubowiecki.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
    	scene = new Scene(loadFXML("controller/start-screen"));
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    // Liest die FXML-Datei ein und produziert die passende Oberfläche
    public static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        ResourceBundle bundle = ResourceBundle.getBundle("de.lubowiecki.client.lang.ui", Locale.getDefault());
        return fxmlLoader.load(App.class.getResource(fxml + ".fxml"), bundle);
    }

    public static void main(String[] args) {
        launch();
    }

}