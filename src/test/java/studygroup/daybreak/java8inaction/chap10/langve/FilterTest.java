package studygroup.daybreak.java8inaction.chap10.langve;

import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;

public class FilterTest {
    @Test
    public void Filter() {
        // given
        int age = 30;
        Insurance insurance = new Insurance("happy");
        Car car = new Car(Optional.ofNullable(insurance));
        Person person = new Person(Optional.ofNullable(car), age);
        Optional<Person> personOpt = Optional.ofNullable(person);

        // when
        String name = Filter.filter(personOpt, age);

        // then
        Assert.assertEquals("happy", name);
    }

    @Test
    public void Filter_어린경우() {
        // given
        int age = 30;
        Insurance insurance = new Insurance("happy");
        Car car = new Car(Optional.ofNullable(insurance));
        Person person = new Person(Optional.ofNullable(car), 20);
        Optional<Person> personOpt = Optional.ofNullable(person);

        // when
        String name = Filter.filter(personOpt, age);

        // then
        Assert.assertEquals("Unknown", name);
    }
}
