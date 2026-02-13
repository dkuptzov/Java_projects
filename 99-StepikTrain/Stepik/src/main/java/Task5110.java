import com.google.gson.*;
import java.util.*;
import java.util.stream.Collectors;

/*
У вас есть переменная tags, которая содержит входные пользовательские данные.
tags - список из элементов строк.
Напишите код, который считает количество тегов в списке tags и записывает
отсортированный результат в том порядке в котором теги были найдены через
запятую в переменную result.

Sample Input:
["action", "adventure", "strategy", "simulation", "sports", "racing", "puzzle", "strategy", "simulation", "sports"]
Sample Output:
action: 1, adventure: 1, strategy: 2, simulation: 2, sports: 2, racing: 1, puzzle: 1
 */

public class Task5110 {
    public static void main(String[] args) {
        List<String> tags = readInput();
        String result = "";
        if (tags != null) {
            Map<String, Integer> frequency = new LinkedHashMap<>();
            for (String item : tags) {
                frequency.put(item, frequency.getOrDefault(item, 0) + 1);
            }
            result = frequency.entrySet().stream()
                    .map(e -> e.getKey() + ": " + e.getValue())
                    .collect(Collectors.joining(", "));
        }
        System.out.println(result);
    }

    public static List<String> readInput() {
        Scanner scanner = new Scanner(System.in);
        String input;
        if (scanner.hasNextLine()) {
            input = scanner.nextLine();
            Gson gson = new Gson();
            String[] dataArray = gson.fromJson(input, String[].class);
            return Arrays.asList(dataArray);
        }
        scanner.close();
        return null;
    }
}
