class Dog extends Animal {
    public final double dogMultiplier = 0.3;

    Dog(String animalName, int animalAge, double animalWeight) {
        super(animalName, animalAge, animalWeight);
    }

    @Override
    public String toString() {
        return "Dog name = " + getAnimalName() +
                ", age = " + getAnimalAge() +
                ", mass = " + String.format("%.2f", getAnimalWeight()) +
                ", feed = " + String.format("%.2f", getFeedInfoKg());
    }

    public double getFeedInfoKg() {
        return (getAnimalWeight() * dogMultiplier);
    }
}
