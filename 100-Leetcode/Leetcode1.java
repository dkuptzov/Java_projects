import java.util.Scanner;

public class Leetcode1 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int[] num = new int[input.nextInt()];
        for (int i = 0; i < num.length; i++) {
            num[i] = input.nextInt();
        }
        twoSum(num, input.nextInt());
    }

    public static void twoSum(int[] num, int target) {
        boolean stop = false;
        int[] result = new int[2];
        for (int i = 0; i < num.length; i++) {
            for (int j = i + 1; j < num.length; j++) {
                if (num[i] + num[j] == target) {
                    result[0] = i;
                    result[1] = j;
                    stop = true;
                    break;
                }
            }
            if (stop) {
                break;
            }
        }
        System.out.println(result[0]);
        System.out.println(result[1]);
    }
}
