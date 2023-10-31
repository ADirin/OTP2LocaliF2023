package com.example.otp2localdemo1;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class HelloApplication extends Application {
/*
    public HelloApplication() {

    }


    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
       //Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        ResourceBundle rs= ResourceBundle.getBundle("resources.bundle_fi_SU");
        String greeting = rs.getString("greetings");
        String welcome = rs.getString("welcome");
        Label label = new Label(greeting + "\n" + welcome);
        StackPane root = new StackPane(label);
        Scene scene = new Scene(root, 320, 240);


        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }
*/
private ResourceBundle resourceBundle;
    private Label greetingLabel;
    private Button helloButton;

    @Override
    public void start(Stage stage) throws IOException {
        // Initialize the default locale to English
        resourceBundle = ResourceBundle.getBundle("resources.bundle", new Locale("en", "US"));

        // Create UI elements
        greetingLabel = new Label(resourceBundle.getString("greetings"));
        greetingLabel.setFont(Font.font("Yu Gothic UI", 24)); // Set the font size to 24
        greetingLabel.setAlignment(Pos.CENTER); // Center-align the label

        helloButton = new Button(resourceBundle.getString("welcome"));

        // Language selection
        ChoiceBox<String> languageChoiceBox = new ChoiceBox<>();
        languageChoiceBox.getItems().addAll("English", "French", "Finnish", "Japanese");
        languageChoiceBox.setValue("English");

        // Update locale when a new language is selected
        languageChoiceBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                switch (newValue) {
                    case "English":
                        resourceBundle = ResourceBundle.getBundle("resources.bundle", new Locale("en", "US"));
                        break;
                    case "French":
                        resourceBundle = ResourceBundle.getBundle("resources.bundle", new Locale("fr", "FR"));
                        break;
                    case "Finnish":
                        resourceBundle = ResourceBundle.getBundle("resources.bundle", new Locale("fi", "SU"));
                        break;
                    case "Japanese":
                        // clear catch
                        ResourceBundle.clearCache();

                        resourceBundle = ResourceBundle.getBundle("resources.bundle", new Locale("ja", "JP"));                   ;
                        break;
                }
                updateLabels();
            }
        });

        // Button action
        helloButton.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Greetings");
            alert.setHeaderText(resourceBundle.getString("greetings"));
            alert.showAndWait();
        });

        // Layout
        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(languageChoiceBox, greetingLabel, helloButton);
        Scene scene = new Scene(vbox, 320, 240);

        stage.setTitle("JavaFX Localization App");
        stage.setScene(scene);
        stage.show();
    }

    private void updateLabels() {
        greetingLabel.setText(resourceBundle.getString("greetings"));
        helloButton.setText(resourceBundle.getString("welcome"));
    }


    public static void main(String[] args) {
        launch();
    }
}