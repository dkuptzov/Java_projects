import java.util.InputMismatchException;
import java.util.Scanner;

public class Exercise9 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int len = check(input);
        if (len > 0) {
            String[] array = new String[len];
            input.nextLine();
            addInArray(input, array, len);
            String substring = input.nextLine();
            StringBuilder resultString = new StringBuilder();
            if (!substring.isEmpty()) {
                lookingFor(array, substring, resultString);
            }
            else {
                substringIsEmpty(array, resultString);
            }
            System.out.println(resultString);
        }
    }

    public static void substringIsEmpty (String[] array, StringBuilder resultString) {
        /* Вывод всех строк, если строка поиска пустая */
        for (String str : array) {
            if (!resultString.isEmpty()) {
                resultString.append(", ");
            }
            resultString.append(str);
        }
    }

    public static void lookingFor (String[] array, String substring, StringBuilder resultString) {
        /* Поиск подстроки в массиве строк */
        for (String str : array) {
            int count = 0;
            char c = substring.charAt(count);
            for (int word = 0; word < str.length(); word++) {
                char w = str.charAt(word);
                if (c == w) {
                    count++;
                    if (count < substring.length()) {
                        c = substring.charAt(count);
                    }
                    else {
                        if (!resultString.isEmpty()) {
                            resultString.append(", ");
                        }
                        resultString.append(str);
                        break;
                    }
                }
                else {
                    word -= count;
                    count = 0;
                    c = substring.charAt(count);
                }
            }
        }
    }

    public static int check(Scanner input) {
        /* Проверка вводимых значений */
        int result;
        while (true) {
            try {
                result = input.nextInt();
                break;
            }
            catch (InputMismatchException e) {
                input.nextLine();
            }
        }
        return result;
    }

    public static void addInArray (Scanner input, String[] array, int len) {
        /* Добавление строк в массив */
        int count = 0;
        while (count < len) {
            String scString = input.nextLine();
            array[count] = scString;
            count++;
        }
    }
}
