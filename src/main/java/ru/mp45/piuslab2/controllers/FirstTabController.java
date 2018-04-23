package ru.mp45.piuslab2.controllers;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;

import java.util.regex.Pattern;

import static java.lang.Math.abs;
import static java.lang.Math.random;

public class FirstTabController {

    private static final float K = 1.0f;

    // Список графиков
    @FXML
    private ComboBox<String> plotList;

    // Начальная точка
    @FXML
    private TextField startPoint;

    // Конечная точка
    @FXML
    private TextField endPoint;

    // График
    @FXML
    private LineChart chart;

    // Названия графиков
    private String[] graphs = new String[]{
            "Безинерционное звено",
            "Звено с чистым запаздыванием",
            "Апериодическое звено",
            "Черный ящик"
    };

    // Безинерционное звено
    private static float[] f1(float... y) {
        return y;
    }

    // Звено с чистым запаздыванием
    private static float[] f2(int offset, float... y) {
        System.arraycopy(y, 0, y, offset, y.length - offset);
        for (int i = 0; i < offset; i++) y[i] = 0.0f;
        return y;
    }

    private void showAlertDialog() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Ошибка");
        alert.setHeaderText(null);
        alert.setContentText("Неверные значения");

        alert.showAndWait();
    }

    // Апериодическое звено
    private static float[] f3(float t, float... y) {
        for (int i = 1; i < y.length; i++)
            y[i] = (t - 1.0f) / t * y[i - 1] + K / t * y[i];
        return y;
    }

    // Черный ящик
    private static float[] f4(float... y) {
        for (int i = 1; i < y.length; i++) y[i] *= random();
        return y;
    }

    @FXML
    private void initialize() {
        // Добавляем названия графиков в список и выбираем первый
        plotList.getItems().addAll(graphs);
        plotList.getSelectionModel().select(0);
        onClickMethod();

        // Запрещаем вводить буквы в текстовые поля
        Pattern pattern = Pattern.compile("-?\\d*|-?\\d+.\\d*");
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
            // Парсим числа и строим график
            float startPointCoordinate = Float.parseFloat(startPoint.getText());
            float endPointCoordinate = Float.parseFloat(endPoint.getText());
            onGraphChangeListener(plotList.getSelectionModel().getSelectedIndex(), startPointCoordinate, endPointCoordinate);
        } catch (NumberFormatException nfe) {
            showAlertDialog();
        }
    }

    private void onGraphChangeListener(int type, float start, float end) {
        // Формируем массив иксов
        float[] x = new float[100];
        float delta = (end - start) / (float) x.length;
        for (int i = 0; i < x.length; i++)
            x[i] = start + delta * i;
        float[] y;
        y = new float[x.length];
        for (int i = 0; i < x.length; i++) {
            if (abs(x[i]) >= delta * 0.99f) y[i] = 0.0f;
            else y[i] = 1.0f * K;
        }

        // Применяем функцию в зависимости от выбранного варианта
        switch (type) {
            case 0:
                y = f1(y);
                break;
            case 1:
                y = f2((int) (end / 2.0f / delta), y);
                break;
            case 2:
                y = f3(end, y);
                break;
            case 3:
                y = f4(y);
                break;
            default:
                throw new IllegalArgumentException("Неизвестный график: " + type);
        }

        // Выводим данные на экран
        XYChart.Series<Float, Float> series = new XYChart.Series<>();
        for (int i = 0; i < x.length; i++)
            series.getData().add(new XYChart.Data<>(x[i], y[i]));
        chart.getData().clear();
        chart.getData().add(series);
    }
}
