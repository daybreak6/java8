package studygroup.daybreak.java8inaction.chap6.langve;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import studygroup.daybreak.java8inaction.chap5.langve.Dish;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.reducing;

public class CollectorReducingTest {

    private static final Logger logger = LoggerFactory.getLogger(CollectorReducingTest.class);

    @Test
    public void reducing_quiz_6_1() {
        // given

        // when
        String actual = Dish.menu.stream().map(Dish::getName).collect(reducing((s1, s2) -> s1 + s2)).get();

        //String actual2 = Dish.menu.stream().collect(reducing((d1, d2) -> d1.getName() + d2.getName())).get();
        // complie error - reducing 은 인수로 받은 것과 같은 타입으로 반환을 하므로 맞지 않다

        String actual3 = Dish.menu.stream().collect(reducing("", Dish::getName, (s1, s2) -> s1 + s2));

        // then
        String expected = Dish.menu.stream().map(Dish::getName).collect(joining());

        logger.info("expected : " + expected);
        logger.info("actual : " + actual);
        logger.info("actual3 : " + actual3);

        Assert.assertEquals(expected, actual);
        Assert.assertEquals(expected, actual3);
    }
}
