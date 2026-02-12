import java.util.Scanner;

/*
У вас есть переменные n, m, k, которые содержат входные пользовательские данные.

Волшебник продает волшебные мечи принцам, желающим убить дракона.
У каждого меча есть основная характеристика – число драконьих голов,
которые он срубает за один удар. Драконы имеют свои характеристики,
включая число голов и скорость регенерации голов.

Число n, представляющее число голов, которые меч срубает одним ударом.

Число m, представляющее число голов дракона.

Число k, представляющее число голов, которые дракон может отрастить за раз.

Напишите код, который определяет, сможет ли принц убить дракона, используя определенный меч,
и если да, то сколько ударов потребуется. При этом, бои принцев с драконами всегда
протекают одинаково: принц атакует дракона, а затем прячется за щитом; дракон атакует
огненным дыханием и может восстановить потерянные головы.

Если принц может убить дракона, ваше решение должно записать в переменную result
количество ударов, необходимых для убийства дракона.

Если убить дракона таким мечом невозможно, ваше решение должно записать
в переменную result значение -1.

Sample Input 1:
3 | 6 | 2
Sample Output 1:
4
Sample Input 2:
4 | 4 | 5
Sample Output 2:
1
Sample Input 3:
5 | 10 | 6
Sample Output 3:
-1
 */

public class Task423 {
    public static void main(String[] args) {
        Triple<Integer, Integer, Integer> triple = readInput();
        int n = triple.getFirst();
        int m = triple.getSecond();
        int k = triple.getThird();
        int result = 0, step = 0;
        if (k >= n && m > n) result = -1;
        else {
            while (m > 0) {
                m -= n;
                step++;
                if (m <= 0) break;
                m += k;
            }
            result = step;
        }

        System.out.println(result);
    }

    public static Triple<Integer, Integer, Integer> readInput() {
        Scanner scanner = new Scanner(System.in);
        String[] input = scanner.nextLine().trim().split(" \\| ");
        int n = Integer.parseInt(input[0]);
        int m = Integer.parseInt(input[1]);
        int k = Integer.parseInt(input[2]);
        return new Triple<>(n, m, k);
    }
}

class Triple<A, B, C> {
    private final A first;
    private final B second;
    private final C third;

    public Triple(A first, B second, C third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }

    public A getFirst() {
        return first;
    }

    public B getSecond() {
        return second;
    }

    public C getThird() {
        return third;
    }
}
