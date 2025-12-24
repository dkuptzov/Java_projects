import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class Exercise5 {
    private static final long START_TIME = System.currentTimeMillis();
    static final String ERROR_NUMBER = "Could not parse a number. Please, try again";
    static final String ERROR_AGE = "Incorrect input. Age <= 0";
    static final String ERROR_PET = "Incorrect input. Unsupported pet type";
    static final String DOG = "dog";
    static final String CAT = "cat";
    static final double THOUSAND = 1000.0;
    static final int TIMEOUT = 100;

    static void main(String[] args) throws InterruptedException {
        Scanner input = new Scanner(System.in);
        int animalCount = readInt(input);
        List<Animal> pets = IntStream.range(0, animalCount) // for от 0 до animalCount
                .mapToObj(_ -> createPet(input)) // для каждого i создаем животное
                .filter(Optional::isPresent) // только не пустые
                .map(Optional::get) // для не пустых используем get
                .toList(); // собираем в список
        printResult(pets);
    }

    public static int readNumberRecursive(Scanner input) {
        /* Проверка и получение вводимых значений (цифр)
        Количество животных и их возраст*/
        try {
            int count = input.nextInt();
            input.nextLine();
            return count;
        } catch (InputMismatchException e) {
            System.out.println(ERROR_NUMBER);
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
        if (animal.equals(DOG) || animal.equals(CAT)) {
            String name = input.nextLine();
            int age = readInt(input);
            if (age <= 0) {
                System.out.println(ERROR_AGE);
                return Optional.empty();
            }
            return animal.equals(DOG)
                    ? Optional.of(new Dog(name, age))
                    : Optional.of(new Cat(name, age));
        }
        System.out.println(ERROR_PET);
        return Optional.empty();
    }

    public static void printResult(List<Animal> pets) throws InterruptedException {
        /* Вывод результата */
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < pets.size(); i++) {
            Animal pet = pets.get(i);
            Thread thread = new Thread(() -> {
                try {
                    long startTime = System.currentTimeMillis() - START_TIME;
                    double walkTime = pet.goToWalk();
                    synchronized (System.out) {
                        System.out.printf("%s, start time = %.2f, end time = %.2f%n",
                                pet, startTime / THOUSAND, walkTime + startTime / THOUSAND);
                    }
                } catch (InterruptedException e) {
                    synchronized (System.out) {
                        System.out.println("THREAD ERROR");
                    }
                }
            });
            threads.add(thread);
            thread.start();
            if (i < pets.size() - 1) {
                TimeUnit.MILLISECONDS.sleep(TIMEOUT);
            }
        }
        for (Thread thread : threads) {
            thread.join();
        }
    }
}