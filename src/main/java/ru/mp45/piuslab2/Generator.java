package ru.mp45.piuslab2;


import java.util.Random;

public class Generator {

    public static double kxx(int t, int length, double... points) {
        double result = 0;

        int n = length;

        for (int i = 0; i < n - t; i++) {
            result += points[i] * points[i + t];
        }
        return result / (double) (n - t);

    }

    public static double[] rxx(double... points) {
        int n = points.length;

        int q = n/2;

        double mean = mean(points);

        double[] result = new double[q];

        for (int k = 1; k < q; k++) {
            result[k - 1] = 0;
            for (int i = 0; i < n - q; i++) {
                result[k - 1] += (points[i] - mean) * (points[i + k] - mean);
            }
            result[k - 1] = result[k - 1] / ((double) (n - q));
        }

        return result;
    }

    public static double getPorog2(double sigmaX, int length, double... points) {
        int n = length;

        double result = 0;
        for (int p = 1; p < n; p++) {
            result += (1 - p / (double) n) * kxx(p, length, points);
        }

        result = 1 / (double) n * sigmaX * sigmaX + 2 / (double) n * result;
        return Math.sqrt(Math.abs(result));

    }

    public static double[][] getPorog(int finish, double... points) {

        double[][] result = new double[3][finish - 5];
        double[] mean_temp = new double[finish - 5];
        double mean = 0;
        for (int i = 0; i < 5; i++) {
            mean += points[i];
        }
        mean /= (double) 5;
        for (int i = 5; i < finish; i++) {
            mean = (mean * i + points[i]) / ((double) (i + 1));
            mean_temp[i - 5] = mean;
            double sigmaX = getPorog2(meanSquare(mean_temp), i - 4, mean_temp);
            result[0][i - 5] = 2 * sigmaX;
            result[1][i - 5] = mean;
            result[2][i - 5] = -2 * sigmaX;
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
            points[i] = (rand.nextDouble() - 0.5) * Math.sqrt(12);
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

        double a = (T - 1) /  T;
        double b = 1 /  T;

        result[0] = 0;

        for (int i = 1; i < points.length; i++) {
            result[i] = a * result[i - 1] + b * points[i];
        }


        double sigmaX = meanSquare(points);
        double sigmaY = meanSquare(result);

        double k = sigmaY / sigmaX * Math.sqrt(2 * T);


        for (int i = 1; i < result.length; i++) {
            result[i] *= 5.5*k;
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

    public static double[][] createHistData(double... points) {

        int part = 100;

        double[][] result = new double[part][2];

        double diff = (max(points) - min(points)) / (double) part;
        double min = min(points);

        for (int i = 0; i < part; i++) {
            result[i][0] = min + i * diff + diff / 2;
            result[i][1] = 0;
        }

        for (int i = 0; i < points.length; i++) {
            int index = (int) ((points[i] - min) / diff);
            if (index == part) {
                index--;
            }
            result[index][1] += 1;
        }

        return result;
    }

}
