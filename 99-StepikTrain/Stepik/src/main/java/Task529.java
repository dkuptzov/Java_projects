import com.google.gson.Gson;
import java.util.*;
import java.util.stream.Collectors;

/*
У вас есть переменная data которая содержит входные пользовательские данные.

data - список из элементов логического типа.

Напишите код, который проверяет все ли элементы в списке false и записывает логический результат в переменную result.

Sample Input 1:
[false, false, false]
Sample Output 1:
true
Sample Input 2:
[true, false, true]
Sample Output 2:
false
 */

public class Task529 {
    public static void main(String[] args) {
        List<Boolean> data = readInput();
        boolean result = allFalse(data);
        System.out.println(result);
    }

    public static boolean allFalse(List<Boolean> data) {
        data = data.stream().distinct().collect(Collectors.toList());
        for (boolean d : data) {
            if (d) {
                return false;
            }
        }
        return true;
    }

    public static List<Boolean> readInput() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        boolean[] array = new Gson().fromJson(input, boolean[].class);
        List<Boolean> data = new ArrayList<>();
        for (boolean value : array) {
            data.add(value);
        }
        return data;
    }
}