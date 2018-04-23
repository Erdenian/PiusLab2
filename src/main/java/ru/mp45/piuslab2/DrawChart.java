package ru.mp45.piuslab2;

import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

import static ru.mp45.piuslab2.Generator.*;

public class DrawChart {

    //рисование гистограмм
    public static void setValueToHist(BarChart chart, double... points) {

        //преобразование массива точек в массив удобный для построения гистограмм
        double[][] hist_data = createHistData(points);

        //создаем данные для гистограмм
        XYChart.Series series = new XYChart.Series();

        for (int i = 0; i < hist_data.length; i++) {
            series.getData().add(new XYChart.Data(Double.toString(hist_data[i][0]), hist_data[i][1]));
        }

        //устанавливаем данные на график
        chart.getData().add(series);
        //убираем подписи осей
        chart.getXAxis().setTickLabelsVisible(false);
        chart.getYAxis().setTickLabelsVisible(false);
    }

    //рисование обычных графиков одномерных  массивов
    public static void setValueToChart(LineChart chart, double... points) {

        //подсёт собственных характеристик
        double scale = 1000;
        double M = Math.round(mean(points) * scale) / scale;
        double D = meanSquare(points);
        double rx = Math.round(max(rxx(points)) * scale) / scale;
        D = Math.round(D * scale) / scale;

        //выводим характеристики в название
        chart.setTitle("M(x) = " + M + ", D(x) = " + D + ", max(Rxx(q)) = " + rx);

        XYChart.Series series = new XYChart.Series();

        //заполняем данные для графиков
        for (int i = 0; i < points.length; i++) {
            series.getData().add(new XYChart.Data(i, points[i]));
        }

        //добавляем данные на график
        chart.getData().add(series);

    }

    //исуем пороги и график
    public static void setValueToChart2(LineChart chart, double[][] points) {

        //устанавливаем данные для минимального порога
        XYChart.Series series_porog_min = new XYChart.Series();
        for (int i = 0; i < points[0].length; i++) {

            series_porog_min.getData().add(new XYChart.Data(i, points[0][i]));
        }

        //устанавливаем данные для графика зависимости
        XYChart.Series main_series = new XYChart.Series();
        for (int i = 0; i < points[1].length; i++) {

            main_series.getData().add(new XYChart.Data(i, points[1][i]));
        }

        //устанавливаем данные для максимального порога
        XYChart.Series series_porog_max = new XYChart.Series();
        for (int i = 0; i < points[2].length; i++) {

            series_porog_max.getData().add(new XYChart.Data(i, points[2][i]));
        }

        //устанавливаем данные на графики
        chart.getData().add(series_porog_min);
        chart.getData().add(main_series);
        chart.getData().add(series_porog_max);
    }
}
