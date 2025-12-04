import java.util.Scanner;

public class Exercise8 {
    private static final int NO_ERROR = 1;
    private static final int ERROR_1 = 2;
    private static final int ERROR_2 = 3;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        StringBuilder strNumberBuilder = new StringBuilder();
        String scString = input.nextLine();
        scString = scString.concat(Character.toString(' '));
        String str_number = "";
        int num = 0, count = 0, error = NO_ERROR;
        boolean firstNumber = true;
        for (int i = 0; i < scString.length(); i++) {
            char x = scString.charAt(i);
            if (x != ' ') {
                str_number = str_number.concat(Character.toString(x));
            }
            else {
                if (firstNumber) {
                    try {
                        num = Integer.parseInt(str_number);
                    }
                    catch (NumberFormatException e) {
                        error = ERROR_1;
                    }
                    firstNumber = false;
                }
                else {
                    try {
                        int next_num = Integer.parseInt(str_number);
                        if (next_num > num) {
                            num = next_num;
                        } else {
                            error = ERROR_2;
                        }
                    }
                    catch (NumberFormatException e) {
                        str_number = "";
                        count++;
                        continue;
                    }
                }
                str_number = "";
                count++;
            }
            if (error != NO_ERROR) {
                break;
            }
        }
        output(count, error);
    }

    public static void output(int count, int error) {
        if (error == NO_ERROR) {
            System.out.println("The sequence is ordered in ascending order");
        }
        else if (error == ERROR_1) {
            System.out.println("Input error");
        }
        else if (error == ERROR_2) {
            System.out.println("The sequence is not ordered from the ordinal " +
                    "number of the number " + count);
        }
//        else if (error == ERROR_3) {
//            System.out.println("The sequence is ordered in ascending order");
//        }
    }
}