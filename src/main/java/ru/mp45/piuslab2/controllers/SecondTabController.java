package ru.mp45.piuslab2.controllers;

import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;

import static ru.mp45.piuslab2.DrawChart.setValueToChart;
import static ru.mp45.piuslab2.DrawChart.setValueToHist;
import static ru.mp45.piuslab2.Generator.*;

public class SecondTabController {

    @FXML
    private LineChart chart_first;

    @FXML
    private LineChart chart_second;

    @FXML
    private LineChart chart_third;

    @FXML
    private LineChart chart_first_rxx;

    @FXML
    private LineChart chart_second_rxx;

    @FXML
    private LineChart chart_third_rxx;

    @FXML
    private BarChart hist_first;

    @FXML
    private BarChart hist_second;

    @FXML
    private BarChart hist_third;

    // общее количество точек
    private int N = 1000;


    @FXML
    private void initialize() {

        // получаем реализацию случайных чисел
        double[] points = getRandomPoints(N);

        // рисуем график и гистограмму для случайных чисел
        setValueToChart(chart_first, points);
        setValueToHist(hist_first, points);
        setValueToChart(chart_first_rxx,rxx(points));
        chart_first_rxx.setTitle(null);

        // поулчаем реализацию белого шума
        double[] white_noisy = whiteNoisy(points);

        // рисуем график и гистограмму дяля белого шума
        setValueToChart(chart_second, white_noisy);
        setValueToHist(hist_second, white_noisy);
        setValueToChart(chart_second_rxx,rxx(white_noisy));
        chart_second_rxx.setTitle(null);

        // получаем реализацию окрашенного шума
        double[] color_noisy = colorNoisy(white_noisy);

        // рисуем график и гистограмму окрашенного шума
        setValueToChart(chart_third, color_noisy);
        setValueToHist(hist_third, color_noisy);
        setValueToChart(chart_third_rxx,rxx(color_noisy));
        chart_third_rxx.setTitle(null);

    }



}
