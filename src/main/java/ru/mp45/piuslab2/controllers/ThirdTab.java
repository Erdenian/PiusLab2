package ru.mp45.piuslab2.controllers;

import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import ru.mp45.piuslab2.DrawChart;
import ru.mp45.piuslab2.Generator;

public class ThirdTab {

    @FXML
    private LineChart test;
    @FXML
    private LineChart test2;

    @FXML
    private LineChart chart_first;

    @FXML
    private LineChart chart_first_porog;

    @FXML
    private BarChart hist_first;

    @FXML
    private LineChart chart_second;

    @FXML
    private LineChart chart_second_porog;

    @FXML
    private BarChart hist_second;

    @FXML
    private void initialize() {
        getNext();
    }

    @FXML
    private void getNext(){
        int N = 1000;

        double[] points = Generator.getRandomPoints(N);
        double[] white_noisy = Generator.whiteNoisy(points);
        double[][] porog = Generator.getPorog(1000,white_noisy);

        DrawChart.setValueToChart(chart_first,white_noisy);
        DrawChart.setValueToChart2(chart_first_porog,porog);

        double[] color_noisy = Generator.colorNoisy(white_noisy);
        double[][] porog_color = Generator.getPorog(1000,color_noisy);

        DrawChart.setValueToChart(chart_second,color_noisy);
        DrawChart.setValueToChart2(chart_second_porog,porog_color);

    }

}
