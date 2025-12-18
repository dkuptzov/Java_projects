public class Dog extends Animal {
    Dog(String animalName, int animalAge) {
        super(animalName, animalAge);
    }

    @Override
    public String toString() {
        return "Dog name = " + getAnimalName() + ", age = " + getAnimalAge();
    }
}
