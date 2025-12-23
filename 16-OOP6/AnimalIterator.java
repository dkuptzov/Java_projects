import java.util.List;

public class AnimalIterator implements BaseIterator<Animal> {
    private final List<Animal> pets;
    private int index;

    AnimalIterator(List<Animal> pets) {
        this.pets = pets;
        this.index = 0;
    }

    @Override
    public Animal next() {
        Animal animal = pets.get(index);
        index++;
        return animal;
    }

    @Override
    public boolean hasNext() {
        return index < pets.size();
    }
    @Override
    public void reset() {
        index = 0;
    }
}
