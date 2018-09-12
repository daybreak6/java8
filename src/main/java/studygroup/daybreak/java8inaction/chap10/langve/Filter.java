package studygroup.daybreak.java8inaction.chap10.langve;

import java.util.Optional;

public class Filter {
    public static String filter(Optional<Person> person, int maxAge) {
        return person.filter(p -> p.getAge() >= maxAge)
                .flatMap(Person::getCar)
                .flatMap(Car::getInsurance)
                .map(Insurance::getName)
                .orElse("Unknown");
    }
}
