package studygroup.daybreak.java8inaction.chap10.langve;

import java.util.Optional;

public class FlatMap {
    public static Optional<Insurance> oldSchoolnullSafeFindCheapestInsurance(Optional<Person> person, Optional<Car> car) {
        if (person.isPresent() && car.isPresent()) {
            return Optional.of(findCheapestInsurance(person.get(), car.get()));
        } else {
            return Optional.empty();
        }
    }

    private static Insurance findCheapestInsurance(Person person, Car car) {
        return car.getInsurance().get();
    }

    public static Optional<Insurance> nullSafeFindCheapestInsurance(Optional<Person> person, Optional<Car> car) {
        return person.flatMap(p -> car.map(c -> findCheapestInsurance(p, c)));
    }
}
