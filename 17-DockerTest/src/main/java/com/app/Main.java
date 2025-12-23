package com.app;

// public class Main {
//     public static void main(String[] args) {
//         System.out.println("Hello from Dockerized Java App!");
//         System.out.println("Arguments received: " + String.join(", ", args));

//         // Демонстрация работы
//         for (int i = 0; i < 3; i++) {
//             System.out.println("Iteration: " + i);
//             try {
//                 Thread.sleep(1000);
//             } catch (InterruptedException e) {
//                 e.printStackTrace();
//             }
//         }
//     }
// }

import java.util.Scanner;
import java.util.InputMismatchException;

public class Main {
    public static void main(String[] args) {
        double[] x = new double[3];
        double[] y = new double[3];
        Scanner input = new Scanner(System.in);
        input(x, y, input);
        boolean isTriangle = square(x, y);
        output(isTriangle, x, y);
    }

    public static void input(double[] x, double[] y, Scanner input) {
        /* Создание двух массивом с координатами x и y */
        int step = 0, len = 0;
        while (step < 6) {
            try {
                if (step % 2 == 0) {
                    x[len] = input.nextDouble();
                } else {
                    y[len] = input.nextDouble();
                    len++;
                }
                step++;
            } catch (InputMismatchException e) {
                System.out.println("Could not parse a number. Please, try again");
                input.nextLine();
            }
        }
    }

    public static double perimeter(double[] x, double[] y) {
        /* Подсчет периметра треугольника */
        return Math.sqrt(Math.pow(x[1] - x[0], 2) + Math.pow(y[1] - y[0], 2)) +
                Math.sqrt(Math.pow(x[2] - x[1], 2) + Math.pow(y[2] - y[1], 2)) +
                Math.sqrt(Math.pow(x[2] - x[0], 2) + Math.pow(y[2] - y[0], 2));
    }

    public static boolean square(double[] x, double[] y) {
        /* Подсчет площади треугольника */
        double square = 0.5 * Math.abs(
                x[0] * (y[1] - y[2]) +
                        x[1] * (y[2] - y[0]) +
                        x[2] * (y[0] - y[1]));
        return Math.abs(square) > 1e-10;
    }

    public static void output(boolean isTriangle, double[] x, double[] y) {
        /* Вывод результата */
        if (isTriangle) {
            System.out.printf("Perimeter: %.3f", perimeter(x, y));
        } else {
            System.out.println("It's not a triangle");
        }
    }
}
