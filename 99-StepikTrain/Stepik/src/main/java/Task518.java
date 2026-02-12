import com.google.gson.Gson;

import java.util.*;

/*
У вас есть переменная data, которая содержит входные пользовательские данные.
data - список из элементов целых чисел.
Напишите код, который сортирует числовые элементы списка data в порядке возрастания,
отсеивает дубликаты и записывает результат через запятую в переменную result.

Sample Input:
[1, 1, 3, 2, 4, 5, 2, 3]
Sample Output:
1, 2, 3, 4, 5
 */

public class Task518 {
    public static void main(String[] args) {
        List<Integer> data = readInput();
        String result = "";
        if (data != null) {
            Collections.sort(data);
            List<String> strings = new ArrayList<>();
            for (int i : data)
                strings.add(Integer.toString(i));
            // Удаление дублей
            List<String> res = strings.stream()
                    .distinct().toList();
            result = String.join(", ", res);
        }
        System.out.println(result);
    }


    public static List<Integer> readInput() {
        Scanner scanner = new Scanner(System.in);
        String input;
        if (scanner.hasNextLine()) {
            input = scanner.nextLine();
            Gson gson = new Gson();
            Integer[] dataArray = gson.fromJson(input, Integer[].class);
            return Arrays.asList(dataArray);
        }
        scanner.close();
        return null;
    }
}
