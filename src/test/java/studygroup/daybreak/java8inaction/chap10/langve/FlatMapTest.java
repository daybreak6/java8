package studygroup.daybreak.java8inaction.chap10.langve;

import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;

public class FlatMapTest {
    @Test
    public void nullCheck_quiz_10_1() {
        // given
        Person p = null;
        Car c = null;

        Optional<Person> personOptional = Optional.ofNullable(p);
        Optional<Car> carOptional = Optional.ofNullable(c);

        // when
        Optional<Insurance> actual = FlatMap.oldSchoolnullSafeFindCheapestInsurance(personOptional, carOptional);

        // then
        Assert.assertEquals(Optional.empty(), actual);
    }

    @Test
    public void flatMap_quiz_10_1() {
        // given
        Person p = null;
        Car c = null;

        Optional<Person> personOptional = Optional.ofNullable(p);
        Optional<Car> carOptional = Optional.ofNullable(c);

        // when
        Optional<Insurance> actual = FlatMap.nullSafeFindCheapestInsurance(personOptional, carOptional);

        // then
        Assert.assertEquals(Optional.empty(), actual);
    }
}
