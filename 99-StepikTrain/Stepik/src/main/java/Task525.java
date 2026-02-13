import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.*;
import java.util.stream.Collectors;

/*
У вас есть переменные n, data которые содержат входные пользовательские данные.
data - список из элементов целых чисел.
Напишите код, который умножает каждый элемент списка на значение переменной n
и записывает отсортированный результат в порядке возрастания в виде строки через запятую
в переменную result.

Sample Input:
2 | [1, 2, 3]
Sample Output:
2, 4, 6
 */

public class Task525 {
    public static void main(String[] args) {
        Pair3<Integer, List<Integer>> input = readInput();
        int n = input.getFirst();
        List<Integer> data = input.getSecond();
        String result = multiplyAndSortList(data, n);
        System.out.println(result);
    }

    public static String multiplyAndSortList(List<Integer> data, int n) {
        data = data.stream().sorted().map(i -> i * n).collect(Collectors.toList());
        return data.stream().map(Object::toString).collect(Collectors.joining(", "));
    }

    public static Pair3<Integer, List<Integer>> readInput() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        Gson gson = new Gson();
        Type listType = new TypeToken<List<Integer>>(){}.getType();
        List<Integer> data = gson.fromJson(input.split(" \\| ")[1], listType);
        int n = Integer.parseInt(input.split(" \\| ")[0]);

        return new Pair3<>(n, data);
    }
}

class Pair3<F, S> {
    private final F first;
    private final S second;

    public Pair3(F first, S second) {
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