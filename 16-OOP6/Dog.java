//import java.util.concurrent.TimeUnit;

class Dog extends Animal {
    Dog(String animalName, int animalAge) {
        super(animalName, animalAge);
    }

    @Override
    public String toString() {
        return "Dog name = " + getAnimalName() +
                ", age = " + getAnimalAge();
    }

//    @Override
//    public double goToWalk() throws InterruptedException {
//        double result = getAnimalAge() * 0.5;
//        TimeUnit.SECONDS.sleep((long)result);
//        return result;
//    }
}
