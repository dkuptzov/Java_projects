public class Cat extends Animal {
    Cat(String animalName, int animalAge) {
        super(animalName, animalAge);
    }

    @Override
    public String toString() {
        return "Cat name = " + getAnimalName() + ", age = " + getAnimalAge();
    }
}
