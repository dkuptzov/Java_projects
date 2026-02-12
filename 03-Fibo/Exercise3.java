import java.util.InputMismatchException;
import java.util.Scanner;

public class Exercise3 {
    private static final int ERROR_OVERFLOW = -1;
    private static final int MAX_RECURSION = 46;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int result = fibonacci(input(input));
        output(result);
    }

    public static int input(Scanner input) {
        /* Ввод числа до которого считаем Фибоначчи */
        int count_input;
        while (true) {
            try {
                count_input = input.nextInt();
                if (count_input >= 0) {
                    break;
                }
                else {
                    System.out.println("Could not parse a number. Please, try again");
                    input.nextLine();
                }
            } catch (InputMismatchException e) {
                System.out.println("Could not parse a number. Please, try again");
                input.nextLine();
            }
        }
        return count_input;
    }

    public static int fibonacci(int n) {
        /* Рекурсия для вычисления числа Фибоначчи, очень медленная  */
        if (n == 0) {
            return 0;
        }
        else if (n == 1) {
            return 1;
        }
        else if (n > MAX_RECURSION) {
            return ERROR_OVERFLOW;
        }
        else {
            return fibonacci(n - 1) + fibonacci(n - 2);
        }
    }

    public static void output (int result) {
        /* Вывод результата */
        if (result == ERROR_OVERFLOW) {
            System.out.println("Too large n");
        }
        else {
            System.out.println(result);
        }
    }
}
