import java.util.Scanner;

/*
У вас есть переменная message, которая содержит входные пользовательские данные.

Напишите код, который в зависимости от длины строки message записывает
количество * в переменную result.

Важно!
Учитывайте то что ваш код должен работать как с кириллицей так и с латиницей.
 */

public class Task4111 {
    public static void main(String[] args) {
        String message = readInput();
        //Изменяемая строка
        StringBuilder result = new StringBuilder();
        int x = message.length();
        while (x-- > 0) result.append("*");

        System.out.println(result.toString());
    }

    public static String readInput() {
        String message = "";

        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNextLine()) {
            message = scanner.nextLine();
        }
        scanner.close();

        return message;
    }
}
