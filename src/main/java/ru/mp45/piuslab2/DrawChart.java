package ru.mp45.piuslab2;

import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import static ru.mp45.piuslab2.Generator.*;

public class DrawChart {


    public static double[][] createHistData(double... points) {

        int part = 10;

        double[][] result = new double[part][2];

        double diff = (max(points) - min(points)) / part;
        double min = min(points);

        for (int i = 0; i < part; i++) {
            result[i][0] = min + i * diff + diff / 2;
            result[i][1] = 0;
        }

        for (int i = 0; i < points.length; i++) {
            int index = (int) ((points[i] - min) / diff);
            if (index == 10) {
                index--;
            }
            result[index][1] += 1;
        }

        return result;
    }

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

        //chart.setTitle("M(x) = " + M + ", D(x) = " + D);

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
}
