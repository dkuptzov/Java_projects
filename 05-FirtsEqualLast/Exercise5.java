import java.util.InputMismatchException;
import java.lang.ArrayIndexOutOfBoundsException;
import java.util.Scanner;

public class Exercise5 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int len = check(input);
        int[] array = input(input, len);
        output(first_equal_last(array), len);
    }

    public static int[] input(Scanner input, int len) {
        /* Заполняем массив */
        int[] array = {0};
        input.nextLine();
        if (len > 0) {
            while (true) {
                try {
                    String scString = input.nextLine().trim();
                    String[] scStrings = scString.split(" ");
                    array = new int[len];
                    for (int i = 0; i < len; i++) {
                        array[i] = Integer.parseInt(scStrings[i]);
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

    public static int[] first_equal_last (int[] array) {
        /* Поиск чисел у которых первая цифра равно последней */
        int count = 0;
        for (int i : array) {
            if (equal(i)) {
                count++;
            }
        }
        int[] res = new int[count];
        count = 0;
        for (int i : array) {
            if (equal(i)) {
                res[count++] = i;
            }
        }
        return res;
    }

    public static boolean equal (int number) {
        int x = number;
        while (x >= 10) {
            x /= 10;
        }
        return (x == number % 10);
    }

    public static void output (int[] array, int len) {
        if (len <= 0) {
            System.out.println("Input error. Size <= 0");
        }
        else if (array.length == 0) {
            System.out.println("There are no such elements");
        }
        else {
            for (int i : array) {
                System.out.print(i + " ");
            }
        }
    }
}
