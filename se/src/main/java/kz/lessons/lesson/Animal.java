package kz.lessons.lesson;

public class Animal implements Pet {
    private String name;
    private int age;

    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }




    public String getName() {
        return this.name;
    }

    public int getAge() {
        return this.age;
    }
}