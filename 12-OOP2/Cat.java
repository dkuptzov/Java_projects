class Cat extends Animal {
    public final double catMultiplier = 0.1;

    Cat(String animalName, int animalAge, double animalWeight) {
        super(animalName, animalAge, animalWeight);
    }

    @Override
    public String toString() {
        return "Cat name = " + getAnimalName() +
                ", age = " + getAnimalAge() +
                ", mass = " + String.format("%.2f", getAnimalWeight()) +
                ", feed = " + String.format("%.2f", getFeedInfoKg());
    }

    public double getFeedInfoKg() {
        return (getAnimalWeight() * catMultiplier);
    }
}
