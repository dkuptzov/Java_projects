class Cat extends Animal implements Omnivore {
    Cat(String animalName, int animalAge) {
        super(animalName, animalAge);
    }

    @Override
    public String hunt() {
        return "I can hunt for mice";
    }

    @Override
    public String toString() {
        return "Cat name = " + getAnimalName() +
                ", age = " + getAnimalAge() + ". " + hunt();
    }
}
