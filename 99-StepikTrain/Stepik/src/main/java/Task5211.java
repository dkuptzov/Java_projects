import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.*;

public class Task5211 {
    public static void main(String[] args) {
        List<List<Integer>> data = readInput();
        List<Integer> result = columnSum(data);
        System.out.println(new Gson().toJson(result));
    }

    public static List<Integer> columnSum(List<List<Integer>> data) {
        IntStream.range(0, grid.size())
                .map(i -> grid.get(i).get(i))
                .sum()
    }

    public static List<List<Integer>> readInput() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        return new Gson().fromJson(input, new TypeToken<List<List<Integer>>>() {}.getType());
    }
}