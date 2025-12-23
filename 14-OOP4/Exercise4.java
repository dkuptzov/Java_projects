import java.util.*;
import java.util.stream.IntStream;

class Exercise4 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int animalCount = readInt(input);
        input.nextLine();
        List<Animal> pets = IntStream.range(0, animalCount)// for от 0 до animalCount
                .mapToObj(_ -> createPet(input))// для каждого i создаем животное
                .filter(Optional::isPresent)// только не пустые
                .map(Optional::get)// для не пустых используем get
                .toList(); // собираем в список
        changeAnimalAge(pets);
        printResult(pets);
    }

    public static int readNumberRecursive(Scanner input) {
        /* Проверка и получение вводимых значений (цифр)
        Количество животных и их возраст*/
        try {
            return input.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Could not parse a number. Please, try again");
            input.nextLine(); // очистка некорректного ввода
            return readNumberRecursive(input); // рекурсивный вызов
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

    public static void addAnimalToList(String animal, String name, int age, List<Animal> pets) {
        /* Добавляем животное в список */
        switch (animal) {
            case ("dog"):
                Dog dog = new Dog(name, age);
                pets.add(dog);
                break;
            case ("cat"):
                Cat cat = new Cat(name, age);
                pets.add(cat);
                break;
            default:
                break;
        }
    }

    public static void changeAnimalAge(List<Animal> pets) {
        /* Изменение возраста животных, если возраст больше 10 лет */
        pets.forEach(pet -> {
            if (pet.getAnimalAge() > 10) {
                pet.setAnimalAge(pet.getAnimalAge() + 1);
            }
        });
    }

    public static void printResult(List<Animal> pets) {
        /* Вывод результата */
        pets.forEach(System.out::println);
    }
}
