import java.util.Arrays;
import java.util.Scanner;

/*
У вас есть переменные x1, x2, x3, которые содержат входные пользовательские данные.

Напишите код, который находит максимальное и минимальное число из x1, x2, x3 и записывает
результат в переменную result.
 */

public class Task413 {
    public static void main(String[] args) {
        int x1, x2, x3;
        String result = "";

        int[] inputValues = readInput();
        x1 = inputValues[0];
        x2 = inputValues[1];
        x3 = inputValues[2];
        Arrays.sort(inputValues);
        result = "минимальное: " + inputValues[0] + ", максимальное: " + inputValues[2];

        System.out.println(result);
    }

    public static int[] readInput() {
        int[] inputValues = new int[3];

        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            String[] values = input.split(" ");
            for (int i = 0; i < 3; i++) {
                inputValues[i] = Integer.parseInt(values[i]);
            }
        }
        scanner.close();

        return inputValues;
    }
}
