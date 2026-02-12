import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
У вас есть переменная n которая содержит входные пользовательские данные.
Напишите код, который определяет, есть ли у числа n брэйк-пойнт.
"брэйк-пойнтом" называется такая точка в числе, при которой число можно разделить
на две "равные" части, где сумма чисел слева и справа от этой точки равна.

Например, число 35190 можно разделить на 351 и 90, потому что 3 + 5 + 1 = 9 и 9 + 0 = 9.
А например у числа 555 такого брэйк-пойнта нет, так как оно нельзя разделить на две равные части.

Логический результат записать в переменную result.

Важно!
Каждая цифра числа является отдельным числом для целей данной задачи.
Например, число 123 = 1 + 2 + 3, а не 123 = 1 + 23 или 123 = 12 + 3.

Sample Input 1:
159780
Sample Output 1:
true
Sample Input 2:
112
Sample Output 2:
true
Sample Input 3:
10
Sample Output 3:
false
 */

public class Task425 {
    public static void main(String[] args) {
        int n = readInput();
        boolean result = false;
        List<Integer> list = makeList(n);
        result = isBreakPoint(list);
        System.out.println(result);
    }

    public static int readInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    public static List<Integer> makeList(int n) {
        List<Integer> list = new ArrayList<>();
        while (n > 9) {
            list.add(n % 10);
            n /= 10;
        }
        list.add(n);
        return list;
    }

    public static boolean isBreakPoint(List<Integer> list) {
        //Сложение всех чисел из списка
        int sum = list.stream().mapToInt(Integer::intValue).sum();
        if (sum % 2 == 0) {
            sum /= 2;
            for (int i : list) {
                sum -= i;
                if (sum == 0) {
                    return true;
                } else if (sum < 0)
                    return false;
            }
        }
        return false;
    }
}
