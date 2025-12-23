abstract class Animal {
    private final String name;
    private int age;

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

    public void setAnimalAge(int newAge) { this.age = newAge; }

}
