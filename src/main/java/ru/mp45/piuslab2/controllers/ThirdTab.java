package ru.mp45.piuslab2.controllers;

import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import ru.mp45.piuslab2.DrawChart;
import ru.mp45.piuslab2.Generator;

public class ThirdTab {

    @FXML
    private LineChart chart_first;

    @FXML
    private LineChart chart_first_porog;

    @FXML
    private BarChart bar_fisrt;

    @FXML
    private LineChart chart_second;

    @FXML
    private LineChart chart_second_porog;

    @FXML
    private BarChart bar_second;

    @FXML
    private void initialize() {

        int N = 1000;

        double[] points = Generator.getRandomPoints(N);

        double[] white_noisy = Generator.whiteNoisy(points);

        double[] color_noisy = Generator.colorNoisy(white_noisy);

        DrawChart.setValueToChart(chart_first,points);

        double[][] porog = Generator.getPorog(points);


        DrawChart.setValueToChart2(chart_first_porog,porog);

        DrawChart.setValueToChart(chart_second,color_noisy);

    }


}
