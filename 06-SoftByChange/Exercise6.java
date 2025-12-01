import java.util.InputMismatchException;
import java.lang.ArrayIndexOutOfBoundsException;
import java.util.Scanner;

public class Exercise6 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int len = check(input);
        double[] array = input(input, len);
        sort_by_change(array);
        output(array, len);
    }

    public static double[] input(Scanner input, int len) {
        /* Заполняем массив */
        double[] array = {0};
        input.nextLine();
        if (len > 0) {
            while (true) {
                try {
                    String scString = input.nextLine().trim();
                    String[] scStrings = scString.split(" ");
                    array = new double[len];
                    for (int i = 0; i < len; i++) {
                        array[i] = Double.parseDouble(scStrings[i]);
                    }
                    break;
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    System.out.println("Could not parse a number. Please, try again");
                }
            }
        }
        return array;
    }

    public static int check(Scanner input) {
        /* Проверка вводимых значений */
        int result;
        while (true) {
            try {
                result = input.nextInt();
                break;
            }
            catch (InputMismatchException e) {
                System.out.println("Could not parse a number. Please, try again");
                input.nextLine();
            }
        }
        return result;
    }

    public static void sort_by_change (double[] array) {
        /* Сортировка массива выбором, находим минимальное число и
        меняем его с первым элементом и тд */
        int position_min = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[position_min] > array[j]) {
                    position_min = j;
                }
            }
            double temp = array[i];
            array[i] = array[position_min];
            array[position_min] = temp;
            position_min = i + 1;
        }
    }

    public static void output (double[] array, int len) {
        /* Вывод */
        if (len <= 0) {
            System.out.println("Input error. Size <= 0");
        }
        else {
            for (double i : array) {
                System.out.print(i + " ");
            }
        }
    }
}
