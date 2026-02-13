import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.*;
import java.util.stream.Collectors;

public class Task524 {
    public static void main(String[] args) {
        Pair2<Integer, List<Integer>> input = readInput();
        int n = input.getFirst();
        List<Integer> data = input.getSecond();
        String result = moveItems(n, data);
        System.out.println(result);
    }

    public static String moveItems(int n, List<Integer> items) {
        List<Integer> newItems = new ArrayList<>();
        newItems.addAll(items.subList(n, items.size()));
        newItems.addAll(items.subList(0, n));
//        int nn = 0;
//        for (int i : items) {
//            if (nn >= n)
//                newItems.add(i);
//            nn++;
//        }
//        for (int i : items) {
//            if (n == 0) break;
//            newItems.add(i);
//            n--;
//        }
        return newItems.stream().map(Object::toString).collect(Collectors.joining(", "));
    }

    public static Pair2<Integer, List<Integer>> readInput() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        Gson gson = new Gson();
        Type listType = new TypeToken<List<Integer>>(){}.getType();
        List<Integer> data = gson.fromJson(input.split(" \\| ")[1], listType);
        int n = Integer.parseInt(input.split(" \\| ")[0]);

        return new Pair2<>(n, data);
    }
}

class Pair2<F, S> {
    private final F first;
    private final S second;

    public Pair2(F first, S second) {
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