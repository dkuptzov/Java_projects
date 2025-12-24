abstract class Animal {
    private final String name;
    private final int age;
    private final double weight;

    Animal (String animalName, int animalAge, double animalWeight) {
        name = animalName;
        age = animalAge;
        weight = animalWeight;
    }

    public String getAnimalName() {
        return name;
    }

    public int getAnimalAge() {
        return age;
    }

    public double getAnimalWeight() {
        return weight;
    }

    public abstract double getFeedInfoKg();
}
