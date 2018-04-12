package ru.mp45.piuslab2;

import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

import java.util.Random;

public class Generator {

    public static double[][] getPorog(double... points) {

        double[][] result = new double[3][points.length];
        double[] temp;
        for (int i = 0; i < points.length; i++) {
            temp = getSub(i + 1, points);
            double sigmaX = meanSquare(temp);
            double mean = mean(temp);
            result[0][i] = sigmaX;
            result[1][i] = mean;
            result[2][i] = -sigmaX;
        }
        return result;
    }

    public static double[] getSub(int n, double... points) {
        double[] result = new double[n];

        for (int i = 0; i < n; i++) {
            result[i] = points[i];
        }
        return result;
    }

    public static double[] getRandomPoints(int N) {

        Random rand = new Random();

        double[] points = new double[9 * N];

        for (int i = 0; i < 9 * N; i++) {
            points[i] = rand.nextGaussian();
        }

        return points;
    }

    public static double[] whiteNoisy(double... points) {
        double[] result = new double[points.length / 9];

        for (int i = 0; i < points.length / 9; i++) {
            result[i] = 0;
            for (int j = 0; j < 9; j++) {
                result[i] += points[9 * i + j];
            }
            result[i] /= 3;
        }

        return result;
    }

    public static double[] colorNoisy(double... points) {

        double[] result = new double[points.length];

        //double T = points.length;
        double T = 12;

        double a = (T - 1) / T;
        double b = 1 / T;

        result[0] = 0;

        for (int i = 1; i < points.length; i++) {
            result[i] = a * result[i - 1] + b * points[i];
        }


        double sigmaX = meanSquare(points);
        double sigmaY = meanSquare(result);

        double k = sigmaY / sigmaX * Math.sqrt(2 * T);

        for (int i = 0; i < result.length; i++) {
            result[i] *= k;
        }

        return result;
    }

    public static double meanSquare(double... points) {

        double result = 0;

        double mean = mean(points);

        for (int i = 0; i < points.length; i++) {
            result += (points[i] - mean) * (points[i] - mean);
        }

        result /= points.length;
        result = Math.sqrt(result);

        return result;
    }

    public static double mean(double... points) {
        double mean = 0;

        for (int i = 0; i < points.length; i++) {
            mean += points[i];
        }

        return mean / points.length;
    }

    public static double max(double... points) {
        double max = points[0];

        for (int i = 1; i < points.length; i++) {
            if (points[i] > max) {
                max = points[i];
            }
        }

        return max;
    }

    public static double min(double... points) {
        double min = points[0];

        for (int i = 1; i < points.length; i++) {
            if (points[i] < min) {
                min = points[i];
            }
        }

        return min;
    }

}
