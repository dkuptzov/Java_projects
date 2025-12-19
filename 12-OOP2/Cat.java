class Cat extends Animal {
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
        return (getAnimalWeight() * 0.1);
    }
}
