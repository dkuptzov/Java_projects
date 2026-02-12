import java.util.Scanner;

/*
У вас есть переменные x, y и direction которые содержат входные пользовательские данные.

Игровое поле размера от 0 до 100 по x и от 0 до 100 по y.
x, y содержат числа - стартовая позиция игрока.

direction содержит направление движения, одного из: up, down, left, right.

Напишите код, который высчитывает новую позицию игрока после перемещения
в этом направлении на 1 и записывает результат в переменную result.

Учитывайте то, что новая позиция игрока не должна быть меньше 0 или больше 100 как x так и по y.

Например, если новая позиция игрока больше 100 тогда, устанавливаем ему значение 100,
а если новая позиция игрока меньше 0 тогда устанавливаем ему значение 0 по
x или по y в зависимости от того какую границу игрок перешел.
 */

public class Task415 {
    public static void main(String[] args) {
        int x, y;
        String direction, result = "";

        String[] inputValues = readInput();
        x = Integer.parseInt(inputValues[0]);
        y = Integer.parseInt(inputValues[1]);
        direction = inputValues[2];
        switch (direction) {
            case "down" -> y++;
            case "left" -> x--;
            case "right" -> x++;
            case "up" -> y--;
        }
        x = Math.min(x, 100);
        x = Math.max(x, 0);
        y = Math.min(y, 100);
        y = Math.max(y, 0);
        result = "x: " + x + ", y: " + y + ", direction: " + direction;

        System.out.println(result);
    }

    public static String[] readInput() {
        String[] inputValues = new String[3];

        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            String[] values = input.split(" ");
            for (int i = 0; i < 3; i++) {
                inputValues[i] = values[i];
            }
        }
        scanner.close();

        return inputValues;
    }
}
