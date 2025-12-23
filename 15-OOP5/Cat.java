import java.util.concurrent.TimeUnit;

class Cat extends Animal {
    Cat(String animalName, int animalAge) {
        super(animalName, animalAge);
    }

    @Override
    public String toString() {
        return "Cat name = " + getAnimalName() +
                ", age = " + getAnimalAge();
    }

    @Override
    public double goToWalk() throws InterruptedException {
        double result = getAnimalAge() * 0.25;
        TimeUnit.SECONDS.sleep((long)result);
        return result;
    }
}
