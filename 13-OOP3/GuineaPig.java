class GuineaPig extends Animal implements Herbivore {
    GuineaPig(String animalName, int animalAge) {
        super(animalName, animalAge);
    }

    @Override
    public String chill() {
        return "I can chill for 12 hours";
    }

    @Override
    public String toString() {
        return "GuineaPig name = " + getAnimalName() +
                ", age = " + getAnimalAge() + ". " + chill();
    }
}
