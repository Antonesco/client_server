package kz.lessons.lesson;

/**
 * Created by Elektron on 15.03.2016.
 */
public class Dog implements Pet {

    private final Animal animal;

    public Dog(Animal animal) {
        this.animal = animal;
    }

    public String getName() {
        return this.animal.getName();
    }

    public int getAge() {
        return this.animal.getAge();
    }
}
