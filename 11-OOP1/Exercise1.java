import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Exercise1 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int animalCount = readInt(input);
        List<Animal> pets = new ArrayList<>();
        input.nextLine();
        for (int i = 0; i < animalCount; i++) {
            createAndAddPet(input, pets);
        }
        for (Animal pet : pets) {
            System.out.println(pet);
        }
    }

    public static int readInt(Scanner input) {
        /* Проверка и получение вводимых значений (цифр)
        Количество животных и их возраст*/
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

    public static void createAndAddPet(Scanner input, List<Animal> pets) {
        /* Создаем кошку или собаку */
        String animal = input.nextLine();
        if (animal.equals("dog") || animal.equals("cat")) {
            String name = input.nextLine();
            int age = readInt(input);
            input.nextLine();
            if (age <= 0) {
                System.out.println("Incorrect input. Age <= 0");
            }
            else {
                addAnimalToList(animal, name, age, pets);
            }
        }
        else {
            System.out.println("Incorrect input. Unsupported pet type");
        }
    }

    public static void addAnimalToList(String animal, String name, int age, List<Animal> pets) {
        /* Добавляем животное в список */
        if (animal.equals("dog")) {
            Dog dog = new Dog(name, age);
            pets.add(dog);
        } else {
            Cat cat = new Cat(name, age);
            pets.add(cat);
        }
    }
}
