import java.util.Map;
import java.util.Scanner;

/*
У вас есть переменные message, k которые содержат входные пользовательские данные.

message - строка состоящая из маленьких букв английского алфавита.

k - целое число со значением от -100000 до 100000.

Напишите код, который выполнит следующие действия в зависимости от значения k:

Если k положительное, решение должно создать строку, являющуюся k-ой степенью строки message.
То есть, результатом будет строка, в которой строка message повторяется k раз.

Если k отрицательное, решение должно попытаться найти корень степени k из строки message.
Для этого длина строки message должна быть кратной k.

Если строка message имеет такую структуру, что можно найти корень,
то решение должно создать строку корень степени k.
В противном случае, решение должно создать строку NO SOLUTION.

Если k равно нулю, функция должна вернуть пустую строку.
Результат записать в виде новой строки в переменную result.

Sample Input 1:
ab | 2
Sample Output 1:
abab
Sample Input 2:
ab | 4
Sample Output 2:
abababab
Sample Input 3:
abababab | -2
Sample Output 3:
abab
Sample Input 4:
abababab | -6
Sample Output 4:
NO SOLUTION
Sample Input 5:
abababab | 0
Sample Output 5:
 */

public class Task422 {
    public static void main(String[] args) {
        var pair = readInput();
        String message = pair.getFirst();
        int k = pair.getSecond();
        String result = "";
        int len = message.length();
        if (k > 0) result = message.repeat(k);
        else if (k < 0 && len % Math.abs(k) == 0) {
            result = message.substring(0, len / Math.abs(k));
        } else if (k < 0) {
            result = "NO SOLUTION";
        }
        System.out.println(result);
    }

    public static Pair<String, Integer> readInput() {
        Scanner scanner = new Scanner(System.in);
        String[] input = scanner.nextLine().trim().split(" \\| ");
        String message = input[0];
        int k = Integer.parseInt(input[1]);
        return new Pair<>(message, k);
    }
}

class Pair<F, S> {
    private final F first;
    private final S second;

    public Pair(F first, S second) {
        this.first = first;
        this.second = second;
    }

    public F getFirst() {
        return first;
    }

    public S getSecond() {
        return second;
    }
}
