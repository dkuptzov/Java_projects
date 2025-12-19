class Hamster extends Animal implements Herbivore {
    Hamster(String animalName, int animalAge) {
        super(animalName, animalAge);
    }

    @Override
    public String chill() {
        return "I can chill for 8 hours";
    }

    @Override
    public String toString() {
        return "Hamster name = " + getAnimalName() +
                ", age = " + getAnimalAge() + ". " + chill();
    }
}