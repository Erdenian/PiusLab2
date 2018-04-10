package ru.mp45.piuslab2.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TabPane;
import ru.mp45.piuslab2.MainApplication;

public class MainController {

    @FXML
    private TabPane mainPane;

    @FXML
    private void initialize() {
        mainPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        mainPane.setTabMinWidth(MainApplication.primaryScreenBounds.getWidth() / 3);
        mainPane.setTabMaxWidth(MainApplication.primaryScreenBounds.getWidth() / 3);
    }
}
