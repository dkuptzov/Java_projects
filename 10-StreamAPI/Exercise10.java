import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Exercise10 {
    public static void main(String[] args) {
        List<User> users = new ArrayList<>();
        Scanner input = new Scanner(System.in);
        int userCount = check(input), count = 0, age;
        String name;
        while (count < userCount) {
            input.nextLine();
            while (true) {
                try {
                    name = input.nextLine();
                    age = input.nextInt();
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Could not parse a number. Please, try again");
                    input.nextLine();
                }
            }
            if (age <= 0) {
                System.out.println("Incorrect input. Age <= 0");
                count--;
            } else {
                User user = new User(name, age);
                users.add(user);
            }
            count++;
        }
        List<String> adultNames = users.stream()
                .filter(user -> user.getAge() >= 18)
                .map(User::getName)
                .toList();
        String result = String.join(", ", adultNames);
        System.out.println(result);
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
                System.out.println("Could not parse a number. Please, try again");
                input.nextLine();
            }
        }
        return result;
    }
}
