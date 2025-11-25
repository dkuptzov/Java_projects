import java.util.InputMismatchException;
import java.util.Scanner;

public class Exercise2 {
    public static void main(String[] args) {
        String output_string = "";
        Scanner input_seconds = new Scanner(System.in);
        int seconds = input(input_seconds);
        if (seconds >= 0) {
            output_string = whatsTime(seconds);
        }
        output(output_string, seconds);
    }

    public static int input(Scanner input) {
        /* Ввод количества секунд */
        int seconds;
        while (true) {
            try {
                seconds = input.nextInt();
                break;
            }
            catch (InputMismatchException e) {
                System.out.println("Could not parse a number. Please, try again");
                input.nextLine();
            }
        }
        return seconds;
    }

    public static String whatsTime(int seconds) {
        /* Создание форматной строки времени Часы:Минуты:Секунды */
        int hours = seconds / 3600;
        seconds -= (hours * 3600);
        int minutes = seconds / 60;
        seconds -= (minutes * 60);
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    public static void output(String string, int seconds) {
        /* Вывод результата */
        if (seconds >= 0) {
            System.out.println(string);
        }
        else {
            System.out.println("Incorrect time");
        }
    }
}
