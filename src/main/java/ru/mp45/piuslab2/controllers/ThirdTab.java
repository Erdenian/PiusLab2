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
    private BarChart hist_first;

    @FXML
    private LineChart chart_second;

    @FXML
    private LineChart chart_second_porog;

    @FXML
    private BarChart hist_second;

    @FXML
    private void initialize() {

        int N = 1000;

        double[] points = Generator.getRandomPoints(N);
        double[] white_noisy = Generator.whiteNoisy(points);
        double[] rxx = Generator.rxx(white_noisy);
        double[][] porog = Generator.getPorog(1000,white_noisy);

        for(int i =0;i<rxx.length;i++){
            System.out.println(rxx[i]);
        }

        DrawChart.setValueToChart(chart_first,white_noisy);
        DrawChart.setValueToChart2(chart_first_porog,porog);
        DrawChart.setValueToHist(hist_first,rxx);

        double[] color_noisy = Generator.colorNoisy(white_noisy);
        double[] rxx_color = Generator.rxx(color_noisy);
        double[][] porog_color = Generator.getPorog(1000,color_noisy);

        DrawChart.setValueToChart(chart_second,color_noisy);
        DrawChart.setValueToChart2(chart_second_porog,porog_color);
        DrawChart.setValueToHist(hist_second,rxx_color);

    }


}
