package ru.mp45.piuslab2.controllers;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import ru.mp45.piuslab2.DrawChart;
import ru.mp45.piuslab2.Generator;

public class ThirdTabController {

    @FXML
    private LineChart chart_first;

    @FXML
    private LineChart chart_first_porog;

    @FXML
    private LineChart chart_second;

    @FXML
    private LineChart chart_second_porog;

    //общее количество точек
    private int N = 1000;
    //количество выводимых точек
    private int scale = N;

    @FXML
    private void initialize() {

        // получаем реализацию случайных чисел
        double[] points = Generator.getRandomPoints(N);
        // получаем реализацию белого шума
        double[] white_noisy = Generator.whiteNoisy(points);
        // считаем пороги и зависисимость для белого шума
        double[][] porog = Generator.getPorog(scale, white_noisy);

        // рисуем белый шум и пороги
        DrawChart.setValueToChart(chart_first, white_noisy);
        DrawChart.setValueToChart2(chart_first_porog, porog);

        // получаем реализацию окрашенного шума
        double[] color_noisy = Generator.colorNoisy(white_noisy);
        // считаем пороги и зависимость для окрашенного шума
        double[][] porog_color = Generator.getPorog(scale, color_noisy);

        // рисуем окрашеный шум и пороги
        DrawChart.setValueToChart(chart_second, color_noisy);
        DrawChart.setValueToChart2(chart_second_porog, porog_color);

    }

    @FXML
    private void getNext() {
        // получаем реализацию случайных чисел
        double[] points = Generator.getRandomPoints(N);
        // получаем реализацию белого шума
        double[] white_noisy = Generator.whiteNoisy(points);
        // считаем пороги и зависисимость для белого шума
        double[][] porog = Generator.getPorog(scale, white_noisy);

        // рисуем белый шум и пороги
        chart_first.getData().clear();
        DrawChart.setValueToChart(chart_first, white_noisy);
        DrawChart.setValueToChart(chart_first_porog, porog[1]);

        // получаем реализацию окрашенного шума
        double[] color_noisy = Generator.colorNoisy(white_noisy);
        // считаем пороги и зависимость для окрашенного шума
        double[][] porog_color = Generator.getPorog(scale, color_noisy);


        // рисуем окрашеный шум и пороги
        chart_second.getData().clear();
        DrawChart.setValueToChart(chart_second, color_noisy);
        DrawChart.setValueToChart(chart_second_porog, porog_color[1]);

    }

}
