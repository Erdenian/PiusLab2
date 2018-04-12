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

        Pattern pattern = Pattern.compile("\\d*|\\d+.\\d*");
        startPoint.setTextFormatter(new TextFormatter<>(change ->
                pattern.matcher(change.getControlNewText()).matches() ? change : null
        ));
        endPoint.setTextFormatter(new TextFormatter<>(change ->
                pattern.matcher(change.getControlNewText()).matches() ? change : null
        ));
    }

    @FXML
    public void onClickMethod() {
        try {
            float startPointCoordinate = Float.parseFloat(startPoint.getText());
            float endPointCoordinate = Float.parseFloat(endPoint.getText());
            onGraphChangeListener(plotList.getSelectionModel().getSelectedIndex(), startPointCoordinate, endPointCoordinate);
        } catch (NumberFormatException nfe) {
            showAlertDialog();
        }
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
