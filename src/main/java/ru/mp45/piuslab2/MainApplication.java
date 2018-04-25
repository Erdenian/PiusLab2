package ru.mp45.piuslab2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApplication extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/layouts/main.fxml"));
        Scene scene = new Scene(root, 800.0, 480.0);

        primaryStage.setTitle("Лабораторная работа 2");
        primaryStage.setScene(scene);

        primaryStage.setResizable(false);

        primaryStage.show();
    }
}
