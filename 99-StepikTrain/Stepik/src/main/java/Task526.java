import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.*;

/*
У вас есть переменные health и items, которые содержат входные пользовательские данные.
items - список из элементов строк.
Напишите код, который увеличивает значение переменной health в зависимости от того,
сколько зелья находится в списке items и записывается результат в переменную result.

Важно!

Одно "Зелье" это +10 к health
Итоговое значение переменной result не должно быть больше 100. Если значение переменной
result получается больше 100, тогда устанавливаем значении переменной result равной 100.
Sample Input 1:
70 | ["Меч", "Щит", "Свиток", "Зелье"]
Sample Output 1:
80
Sample Input 2:
70 | ["Меч", "Щит", "Зелье", "Свиток", "Зелье"]
Sample Output 2:
90
 */

public class Task526 {
    public static void main(String[] args) {
        Pair4<Integer, List<String>> input = readInput();
        int power = input.getFirst();
        List<String> items = input.getSecond();
        int result = increaseHealth(power, items);
        System.out.println(result);
    }

    public static int increaseHealth(int power, List<String> items) {
//        power += (int) items.stream()
//                .filter("Зелье"::equals)
//                .count() * 10;
        for (String i : items) {
            if (i.equals("Зелье"))
                power += 10;
        }
        return Math.min(100, power);
    }

    public static Pair4<Integer, List<String>> readInput() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        Gson gson = new Gson();
        Type listType = new TypeToken<List<String>>(){}.getType();
        List<String> items = gson.fromJson(input.split(" \\| ")[1], listType);
        int power = Integer.parseInt(input.split(" \\| ")[0]);

        return new Pair4<>(power, items);
    }
}

class Pair4<F, S> {
    private final F first;
    private final S second;

    public Pair4(F first, S second) {
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