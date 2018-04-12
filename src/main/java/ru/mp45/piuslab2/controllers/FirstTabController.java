package ru.mp45.piuslab2.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;

import java.util.regex.Pattern;

public class FirstTabController {

    @FXML
    private ComboBox<String> plotList;
    @FXML
    private TextField startPoint;
    @FXML
    private TextField endPoint;

    private String[] graphs = new String[]{
            "Безинерционное звено",
            "Звено с чистым запаздыванием",
            "Опериодическое звено",
            "Черный ящик"
    };

    @FXML
    private void initialize() {
        plotList.getItems().addAll(graphs);
        plotList.getSelectionModel().select(0);

        Pattern pattern = Pattern.compile("\\d*|\\d+,\\d*");

        TextFormatter formatter = new TextFormatter<>(change ->
                pattern.matcher(change.getControlNewText()).matches() ? change : null);
        TextFormatter formatter2 = new TextFormatter<>(change ->
                pattern.matcher(change.getControlNewText()).matches() ? change : null);

        startPoint.setTextFormatter(formatter);
        endPoint.setTextFormatter(formatter2);
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

        onGraphChangeListener(plotList.getSelectionModel().getSelectedIndex(), startPointCoordinate, endPointCoordinate);
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

    private void onGraphChangeListener(int type, float start, float end) {
        System.out.println(graphs[type] + " " + start + " " + end);
    }
}
