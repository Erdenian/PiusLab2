package ru.mp45.piuslab2;


import java.util.Random;

public class Generator {

    //подсчет ковариации по формуле
    public static double kxx(int t, int length, double... points) {
        double result = 0;

        int n = length;

        for (int i = 0; i < n - t; i++) {
            result += points[i] * points[i + t];
        }
        return result / (double) (n - t);

    }

    //подсчет кореляции по формле
    public static double[] rxx(double... points) {
        int n = points.length;

        int q = n / 10;

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

    //подсчет только пороговой функции по формуле
    public static double getPorog2(double sigmaX, int length, double... points) {
        int n = length;

        double result = 0;
        for (int p = 1; p < n; p++) {
            result += (1 - p / (double) n) * kxx(p, length, points);
        }

        result = 1 / (double) n * sigmaX * sigmaX + 2 / (double) n * result;
        return Math.sqrt(Math.abs(result));

    }

    //подсчет пороговых фукций и зависимости
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

    //последовательность случайных чисел равномерного распределение
    public static double[] getRandomPoints(int N) {

        Random rand = new Random();

        double[] points = new double[9 * N];

        for (int i = 0; i < 9 * N; i++) {
            points[i] = rand.nextDouble();
        }

        points = norm(points);

        return points;
    }

    //получение реализации белого шума
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

    //поулчение реализации окрашенного шума
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

        result = norm(result);

        return result;
    }

    //нормировка (среднее = 0, отклонение = 1)
    public static double[] norm(double... points) {
        double[] result = new double[points.length];

        double sigmaY = meanSquare(points);

        double mean = mean(points);

        for (int i = 1; i < points.length; i++) {
            result[i] = points[i] - mean;
            result[i] /= sigmaY;
        }

        return result;
    }

    //подсчет среднеквадратичного отклонения
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

    //подсчет среднего
    public static double mean(double... points) {
        double mean = 0;

        for (int i = 0; i < points.length; i++) {
            mean += points[i];
        }

        return mean / points.length;
    }

    //нахождение максимального элемента
    public static double max(double... points) {
        double max = points[0];

        for (int i = 1; i < points.length; i++) {
            if (points[i] > max) {
                max = points[i];
            }
        }

        return max;
    }

    //нахожедние минимального элемента
    public static double min(double... points) {
        double min = points[0];

        for (int i = 1; i < points.length; i++) {
            if (points[i] < min) {
                min = points[i];
            }
        }

        return min;
    }

    //создание последовательности удобной для рисования гистограмм
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
