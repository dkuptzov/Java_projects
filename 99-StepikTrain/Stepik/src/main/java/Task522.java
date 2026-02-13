import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.*;

/*
У вас есть переменная data, которая содержит входные пользовательские данные.
data - двумерный список из элементов целых чисел.
Напишите код, который находит минимальное число в двумерном списке произвольного
размера и записывает результат в переменную result.

Sample Input:
[[2, 3, 1],[4, 42, 6],[7, 8, 9]]
Sample Output:
1
 */

public class Task522 {
    public static void main(String[] args) {
        List<List<Integer>> data = readInput();
        int result;

        result = findMinNumber(data);

        System.out.println(result);
    }

    public static int findMinNumber(List<List<Integer>> data) {
        List<Integer> newData = new ArrayList<>();
        data.forEach(newData::addAll);
        return newData.stream()
                .mapToInt(Integer::intValue)
                .min()
                .orElse(0);
    }

    public static List<List<Integer>> readInput() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        Gson gson = new Gson();
        Type listType = new TypeToken<List<List<Integer>>>(){}.getType();
        return gson.fromJson(input, listType);
    }
}