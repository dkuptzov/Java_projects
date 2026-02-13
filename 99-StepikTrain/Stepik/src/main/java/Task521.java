import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.*;
import java.util.stream.Collectors;

/*
У вас есть переменная data, которая содержит входные пользовательские данные.
data - список из элементов целых чисел.
Напишите код, который будет находить четные и нечетные числа и записывать результат в виде строки:
(четные) (нечетные) в переменную result.
Важно! Четные и нечетные числа должны быть отсортированы в порядке возрастания.

Sample Input:
[4, 3, 7, 1, 8, 6, 5, 2]
Sample Output:
(2, 4, 6, 8) (1, 3, 5, 7)
 */

public class Task521 {
    public static void main(String[] args) {
        List<Integer> data = readInput();
        String result;

        result = findEvenOddNumbers(data);

        System.out.println(result);
    }

    public static String findEvenOddNumbers(List<Integer> data) {
        String result = "";
        Collections.sort(data);
        List<Integer> even = new ArrayList<>();
        List<Integer> odd = new ArrayList<>();
        for (int i : data) {
            if (i % 2 == 0)
                even.add(i);
            else odd.add(i);
        }
        result = "(" + even.stream()
                .map(Object::toString)
                .collect(Collectors.joining(", ")) + ") ("
                + odd.stream()
                .map(Object::toString)
                .collect(Collectors.joining(", ")) + ")";
        return result;
    }

    public static List<Integer> readInput() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        Gson gson = new Gson();
        Type listType = new TypeToken<List<Integer>>(){}.getType();
        return gson.fromJson(input, listType);
    }
}