package ru.mp45.piuslab2;

import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

import static ru.mp45.piuslab2.Generator.*;

public class DrawChart {


    public static void setValueToHist(BarChart chart, double... points) {

        double[][] hist_data = createHistData(points);

        XYChart.Series series = new XYChart.Series();

        for (int i = 0; i < hist_data.length; i++) {
            series.getData().add(new XYChart.Data(Double.toString(hist_data[i][0]), hist_data[i][1]));
        }

        chart.getData().add(series);
        chart.getXAxis().setTickLabelsVisible(false);
        chart.getYAxis().setTickLabelsVisible(false);
    }

    public static void setValueToChart(LineChart chart, double... points) {

        double scale = 1000;
        double M = Math.round(mean(points) * scale) / scale;
        double D = meanSquare(points);
        //D *= D;
        D = Math.round(D * scale) / scale;

        chart.setTitle("M(x) = " + M + ", D(x) = " + D);

        XYChart.Series series = new XYChart.Series();

        for (int i = 0; i < points.length; i++) {
            series.getData().add(new XYChart.Data(i, points[i]));
        }

        chart.getData().add(series);

        XYChart.Series mean_series = new XYChart.Series();

        double mean = mean(points);

        series.getData().add(new XYChart.Data(0, mean));
        series.getData().add(new XYChart.Data(points.length, mean));

        chart.getData().add(mean_series);

    }

    public static void setValueToChart2(LineChart chart, double[][] points) {

        XYChart.Series series_porog_min = new XYChart.Series();
        for (int i = 0; i < points[0].length; i++) {

            series_porog_min.getData().add(new XYChart.Data(i, points[0][i]));
        }

        XYChart.Series main_series = new XYChart.Series();
        for (int i = 0; i < points[1].length; i++) {

            main_series.getData().add(new XYChart.Data(i, points[1][i]));
        }

        XYChart.Series series_porog_max = new XYChart.Series();
        for (int i = 0; i < points[2].length; i++) {

            series_porog_max.getData().add(new XYChart.Data(i, points[2][i]));
        }


        chart.getData().add(series_porog_min);
        chart.getData().add(main_series);
        chart.getData().add(series_porog_max);
    }
}
