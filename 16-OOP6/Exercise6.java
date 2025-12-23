import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class Exercise6 {
    static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int animalCount = readInt(input);
        input.nextLine();
        List<Animal> pets = IntStream.range(0, animalCount)// for от 0 до animalCount
                .mapToObj(_ -> createPet(input))// для каждого i создаем животное
                .filter(Optional::isPresent)// только не пустые
                .map(Optional::get)// для не пустых используем get
                .toList(); // собираем в список
        printResult(pets);
    }

    public static int readNumberRecursive(Scanner input) {
        /* Проверка и получение вводимых значений (цифр)
        Количество животных и их возраст*/
        try {
            return input.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Could not parse a number. Please, try again");
            input.nextLine();
            return readNumberRecursive(input);
        }
    }

    public static int readInt(Scanner input) {
        /* Вызов рекурсивной функции проверки числа*/
        return readNumberRecursive(input);
    }

    public static Optional<Animal> createPet(Scanner input) {
        String animal = input.nextLine();
        if (animal.equals("dog") || animal.equals("cat")) {
            String name = input.nextLine();
            int age = readInt(input);
            input.nextLine();
            if (age <= 0) {
                System.out.println("Incorrect input. Age <= 0");
                return Optional.empty();
            }
            return animal.equals("dog")
                    ? Optional.of(new Dog(name, age))
                    : Optional.of(new Cat(name, age));
        }
        System.out.println("Incorrect input. Unsupported pet type");
        return Optional.empty();
    }

    public static void printResult(List<Animal> pets) {
        /* Вывод результата */
        AnimalIterator iterator = new AnimalIterator(pets);
        while (iterator.hasNext()) {
            Animal pet = iterator.next();
            System.out.println(pet);
        }
    }
}