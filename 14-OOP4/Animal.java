abstract class Animal {
    private final String name;
    private final int age;

    Animal(String animalName, int animalAge) {
        name = animalName;
        age = animalAge;
    }

    public String getAnimalName() {
        return name;
    }

    public int getAnimalAge() {
        return age;
    }
}
