package ru.mp45.piuslab2.controllers;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import ru.mp45.piuslab2.DrawChart;
import ru.mp45.piuslab2.Generator;

import static ru.mp45.piuslab2.Generator.max;

public class ThirdTab {

    @FXML
    private LineChart chart_first;

    @FXML
    private LineChart chart_first_porog;

    @FXML
    private LineChart chart_second;

    @FXML
    private LineChart chart_second_porog;

    private int N = 1000;

    @FXML
    private void initialize() {

        double[] points = Generator.getRandomPoints(N);
        double[] white_noisy = Generator.whiteNoisy(points);
        double[][] porog = Generator.getPorog(N/3,white_noisy);

        DrawChart.setValueToChart(chart_first,white_noisy);
        DrawChart.setValueToChart2(chart_first_porog,porog);

        double[] color_noisy = Generator.colorNoisy(white_noisy);
        double[][] porog_color = Generator.getPorog(N/3,color_noisy);

        DrawChart.setValueToChart(chart_second,color_noisy);
        DrawChart.setValueToChart2(chart_second_porog,porog_color);

    }

    @FXML
    private void getNext(){

        double[] points = Generator.getRandomPoints(N);
        double[] white_noisy = Generator.whiteNoisy(points);
        double[][] porog = Generator.getPorog(N/3,white_noisy);

        chart_first.getData().clear();
        DrawChart.setValueToChart(chart_first,white_noisy);
        DrawChart.setValueToChart(chart_first_porog,porog[1]);

        double[] color_noisy = Generator.colorNoisy(white_noisy);
        double[][] porog_color = Generator.getPorog(N/3,color_noisy);


        chart_second.getData().clear();
        DrawChart.setValueToChart(chart_second,color_noisy);
        DrawChart.setValueToChart(chart_second_porog,porog_color[1]);

    }

}
