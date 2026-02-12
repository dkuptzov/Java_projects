import com.google.gson.Gson;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.util.List;

/*
У вас есть переменная data, которая содержит входные пользовательские данные.
data - список из элементов строк.
Напишите код, который обращает порядок следования элементов списке data и записывает
результат через запятую в переменную result.

Sample Input:
["Макс", "Дастин", "Майк", "Стив", "Билли"]
Sample Output:
Билли, Стив, Майк, Дастин, Макс
 */

public class Task515 {
    public static void main(String[] args) {
        List<String> data = readInput();
        String result = "";
        if (data != null) {
            Collections.reverse(data);
            // Объединяет все строки без разделителя
            result = String.join(", ", data);
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