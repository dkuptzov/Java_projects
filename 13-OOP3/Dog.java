class Dog extends Animal implements Omnivore {
    Dog(String animalName, int animalAge) {
        super(animalName, animalAge);
    }

    @Override
    public String hunt() {
        return "I can hunt for robbers";
    }

    @Override
    public String toString() {
        return "Dog name = " + getAnimalName() +
                ", age = " + getAnimalAge() + ". " + hunt();
    }
}
