import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.*;

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