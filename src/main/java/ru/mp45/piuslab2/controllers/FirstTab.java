package ru.mp45.piuslab2.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

public class FirstTab {

    @FXML
    private Button okButton;

    @FXML
    private ComboBox<String> plotList;

    private String[] listValues = {"Option 4", "Option 5", "Option 6"};

    @FXML
    private void initialize() {
        plotList.getItems().addAll(listValues);
        plotList.setValue(listValues[0]);
    }

    @FXML
    public void onClickMethod() {
        System.out.print(plotList.getValue());
    }
}
