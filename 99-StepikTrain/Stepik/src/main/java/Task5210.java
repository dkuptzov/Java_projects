import com.google.gson.Gson;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
У вас есть переменныe size, position, data, которые содержат входные пользовательские данные.
data - список из элементов целых чисел.
Напишите код, который заполняет список data до нужного размера size нулями 0 в зависимости от значения position,
которое может принимать значения: left или right.

Важно!
Если размер списка data  больше размера size, тогда в переменную result записываем сообщение:
Неверный размер
Если размер списка data  равен значению переменной size, тогда в переменную result записываем:
Массив data в виде строки через запятую.
Если значение переменной position не равно left или right, тогда в переменную result записываем сообщение:
Неверная позиция
Сначала проверяем на корректность позиции, затем на корректность размера.
Результат записать в виде строки через запятую в переменную result.

Sample Input 1:
5 | left | [1, 2, 3]
Sample Output 1:
0, 0, 1, 2, 3
Sample Input 2:
5 | right | [1, 2, 3, 4, 5]
Sample Output 2:
1, 2, 3, 4, 5
Sample Input 3:
2 | right | [1, 2, 3, 4, 5]
Sample Output 3:
Неверный размер
Sample Input 4:
7 | center | [1, 2, 3, 4, 5, 6, 7]
Sample Output 4:
Неверная позиция
 */

public class Task5210 {
    public static void main(String[] args) {
        Triple2<Integer, String, List<Integer>> input = readInput();
        int size = input.getFirst();
        String position = input.getSecond();
        List<Integer> data = input.getThird();
        String result = fillArray(size, position, data);
        System.out.println(result);
    }

    public static String fillArray(int size, String position, List<Integer> data) {
        String result;
        if (!(position.equals("left") || position.equals("right"))) {
            return "Неверная позиция";
        }
        if (data.size() > size) {
            return "Неверный размер";
        }
        List<Integer> zerosList = IntStream.generate(() -> 0)
                .limit(size - data.size())
                .boxed()
                .toList();
        if (position.equals("left")) {
            result = zerosList.stream().map(Object::toString).collect(Collectors.joining(", ")) +
                    ((size - data.size() > 0) ? ", " : "") +
                    data.stream().map(Object::toString).collect(Collectors.joining(", "));
        } else {
            result = data.stream().map(Object::toString).collect(Collectors.joining(", ")) +
                    ((size - data.size() > 0) ? ", " : "") +
                    zerosList.stream().map(Object::toString).collect(Collectors.joining(", "));
        }
        return result;
    }

    public static Triple2<Integer, String, List<Integer>> readInput() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        String[] values = input.split(" \\| ");
        int size = Integer.parseInt(values[0]);
        String position = values[1];
        List<Integer> data = new Gson().fromJson(values[2], new com.google.gson.reflect.TypeToken<List<Integer>>() {}.getType());

        return new Triple2<>(size, position, data);
    }
}

class Triple2<F, S, T> {
    private final F first;
    private final S second;
    private final T third;

    public Triple2(F first, S second, T third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }

    public F getFirst() {
        return first;
    }

    public S getSecond() {
        return second;
    }

    public T getThird() {
        return third;
    }
}