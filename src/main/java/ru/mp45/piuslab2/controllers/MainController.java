package ru.mp45.piuslab2.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TabPane;

public class MainController {

    @FXML
    private TabPane mainPane;

    @FXML
    private void initialize() {
        mainPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        mainPane.setTabMinWidth(800 / 3.3);
        mainPane.setTabMaxWidth(800 / 3.3);
    }
}
