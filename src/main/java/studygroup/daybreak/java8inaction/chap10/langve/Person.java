package studygroup.daybreak.java8inaction.chap10.langve;

import java.util.Optional;

public class Person {
    private Optional<Car> car;
    private int age;

    public Person(Optional<Car> car, int age) {
        this.car = car;
        this.age = age;
    }

    public Optional<Car> getCar() {
        return car;
    }

    public int getAge() {
        return age;
    }
}
