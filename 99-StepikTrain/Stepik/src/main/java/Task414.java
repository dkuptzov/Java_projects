import java.util.Scanner;

/*
У вас есть переменная stars, которая содержит входные пользовательские данные.

Значение переменной stars от 1 до 5.

1 — ★
2 — ★★
3 — ★★★
4 — ★★★★
5 — ★★★★★

Напишите код, который проверяет значение переменной stars и записывает результат
в переменную result.
 */

public class Task414 {
    public static void main(String[] args) {
        int stars = readInput();
        String result = "★";
        result = result.repeat(stars);
        System.out.println(result);
    }

    public static int readInput() {
        int stars = 0;

        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNextLine()) {
            stars = Integer.parseInt(scanner.nextLine());
        }
        scanner.close();

        return stars;
    }
}
