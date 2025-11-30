import java.util.InputMismatchException;
import java.lang.ArrayIndexOutOfBoundsException;
import java.util.Scanner;

public class Exercise4 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int len = check(input);
        int[] array = input(input, len);
        output(average(array), len);
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

    public static int average (int[] array) {
        int count = 0, sum = 0;
        for (int i : array) {
            if (i < 0) {
                sum += i;
                count++;
            }
        }
        if (count == 0) {
            return 0;
        }
        else {
            return sum / count;
        }
    }

    public static void output (int average, int len) {
        if (len <= 0) {
            System.out.println("Input error. Size <= 0");
        }
        else if (average == 0) {
            System.out.println("There are no negative elements");
        }
        else {
            System.out.println(average);
        }
    }
}
