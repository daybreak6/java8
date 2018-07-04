package studygroup.daybreak.java8inaction.chap3.langve;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import studygroup.daybreak.java8inaction.chap2.langve.FilteringTest;

public class IntegerMultiParamPredicateTest {
    Logger logger = LoggerFactory.getLogger(FilteringTest.class);

    @Test
    public void test_정과질문() {
        // given
        int int1 = 2;
        int int2 = 1;
        int int3 = 0;

        // when
        IntegerMultiParamPredicate predicate = (Integer i1, Integer i2, Integer i3) -> i1 % i2 == 0;
        boolean actual = predicate.test(int1, int2, int3);
        logger.info("actual : " + actual);

        // then
        boolean expected = true;
        Assert.assertEquals(expected, actual);

    }
}
