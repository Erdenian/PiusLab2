package ru.mp45.piuslab2.controllers;

import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

import java.util.Random;

import static ru.mp45.piuslab2.DrawChart.*;
import static ru.mp45.piuslab2.Generator.*;

public class SecondTab {

    @FXML
    private LineChart chart_first;

    @FXML
    private LineChart chart_second;

    @FXML
    private LineChart chart_third;

    @FXML
    private BarChart hist_first;

    @FXML
    private BarChart hist_second;

    @FXML
    private BarChart hist_third;


    private int N = 1000;


    @FXML
    private void initialize() {

        double[] points = getRandomPoints(N);

        setValueToChart(chart_first, points);
        setValueToHist(hist_first, points);

        double[] white_noisy = whiteNoisy(points);

        setValueToChart(chart_second, white_noisy);
        setValueToHist(hist_second, white_noisy);

        double[] color_noisy = colorNoisy(white_noisy);

        setValueToChart(chart_third, color_noisy);
        setValueToHist(hist_third, color_noisy);

    }



}
