import java.util.Map;
import java.util.Scanner;

/*
У вас есть переменная n которая содержит входные пользовательские данные.

n - положительное целое число

У вас есть положительное целое число n, представляющее количество шляп и количество людей. Нужно определить общее количество способов, которыми n шляп можно вернуть n людям таким образом, что ни одна шляпа не возвращается к своему владельцу (т.е., ни один человек не получает свою собственную шляпу).









Эта задача известна как "проблема шляпного чека" или "проблема нарушения перестановок".

Ответ на эту задачу представляет собой количество !n нарушений n-элементного набора.

Нарушение — это такая перестановка элементов множества, при которой ни один элемент
не появляется в исходном положении.

Результат записать в виде числа в переменную result.
 */

public class Task421 {
    public static void main(String[] args) {
        int n = readInput();
        int result;
        result = (int)Math.round(factorial(n) / Math.E);
        System.out.println(result);
    }

    public static int readInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    public static int factorial(int x) {
        int factorial = 1;
        for (int i = 1; i <= x; i++)
            factorial *= i;
        return factorial;
    }
}
