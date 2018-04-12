package ru.mp45.piuslab2.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class FirstTabController {

    @FXML
    private ComboBox<String> plotList;
    @FXML
    private TextField startPoint;
    @FXML
    private TextField endPoint;

    private String[] listValues = {"Plot1", "Plot2", "Plot3", "Plot4"};

    @FXML
    private void initialize() {
        plotList.getItems().addAll(listValues);
        plotList.setValue(listValues[0]);
    }

    @FXML
    public void onClickMethod() {

        float startPointCoordinate;
        float endPointCoordinate;

        try {
            startPointCoordinate = getCoordinateFromField(startPoint);
            endPointCoordinate = getCoordinateFromField(endPoint);
        } catch (NumberFormatException nfe) {
            showAlertDialog();
            return;
        }

        onGraphChangeListener(plotList.getValue(), startPointCoordinate, endPointCoordinate);
    }

    private float getCoordinateFromField(TextField field) throws NumberFormatException {
        return Float.parseFloat(field.getCharacters().toString());
    }

    private void showAlertDialog() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Ошибка");
        alert.setHeaderText(null);
        alert.setContentText("Неверные значения");

        alert.showAndWait();
    }

    private void onGraphChangeListener(String type, float start, float end) {

    }
}
