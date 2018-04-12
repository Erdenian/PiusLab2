package ru.mp45.piuslab2.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.HashMap;

public class FirstTabController {

    @FXML
    private ComboBox<String> plotList;
    @FXML
    private TextField startPoint;
    @FXML
    private TextField endPoint;
    @FXML
    private Label label;

    private HashMap<String, Integer> listValues;

    @FXML
    private void initialize() {
        label.setText("Параметры \n графика: ");
        fillListValues();

        String kindOfPlots[] = new String[listValues.size()];
        listValues.keySet().toArray(kindOfPlots);

        plotList.getItems().addAll(kindOfPlots);
        plotList.setValue(kindOfPlots[0]);
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

        onGraphChangeListener(listValues.get(plotList.getValue()), startPointCoordinate, endPointCoordinate);
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

    private void fillListValues() {
        listValues = new HashMap<>();

        listValues.put("Безинерционное звено", 1);
        listValues.put("Звено с чистым запаздыванием", 2);
        listValues.put("Опериодическое звено", 3);
        listValues.put("Черный ящик", 4);
    }

    private void onGraphChangeListener(int type, float start, float end) {

    }
}
