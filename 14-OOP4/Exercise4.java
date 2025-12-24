import java.util.*;
import java.util.stream.IntStream;

class Exercise4 {
    static final String ERROR_NUMBER = "Could not parse a number. Please, try again";
    static final String ERROR_AGE = "Incorrect input. Age <= 0";
    static final String ERROR_PET = "Incorrect input. Unsupported pet type";
    static final String DOG = "dog";
    static final String CAT = "cat";

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int animalCount = readInt(input);
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
            int count = input.nextInt();
            input.nextLine();
            return count;
        } catch (InputMismatchException e) {
            System.out.println(ERROR_NUMBER);
            input.nextLine();
            return readNumberRecursive(input); // рекурсивный вызов
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

    public static void printResult(List<Animal> pets) {
        /* Вывод результата */
        List<Animal> updatePets = pets.stream()
                .map(pet -> {
                    if (pet.getAnimalAge() > 10) {
                        if (pet instanceof Dog) {
                            return new Dog(pet.getAnimalName(), pet.getAnimalAge() + 1);
                        } else if (pet instanceof Cat) {
                            return new Cat(pet.getAnimalName(), pet.getAnimalAge() + 1);
                        }
                    }
                    return pet;
                })
                .toList();
        updatePets.forEach(System.out::println);
    }
}
