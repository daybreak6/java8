package studygroup.daybreak.java8inaction.chap3.langve;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import studygroup.daybreak.java8inaction.chap2.langve.Apple;
import studygroup.daybreak.java8inaction.util.AppleUtils;

import java.util.Comparator;
import java.util.List;

public class MethodReferenceTest {

    private static final Logger logger = LoggerFactory.getLogger(MethodReferenceTest.class);

    @Test
    public void ByLambda() {
        // given
        List<Apple> apples = AppleUtils.getApples();

        // when
        apples.sort((Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight()));
        logger.info("apples : " + apples);

        // then
        Assert.assertEquals(Integer.valueOf(100), apples.get(0).getWeight());
        Assert.assertEquals(Integer.valueOf(200), apples.get(apples.size() - 1).getWeight());
    }

    @Test
    public void ByMethodReference() {
        // given
        List<Apple> apples = AppleUtils.getApples();

        // when
        apples.sort(Comparator.comparing(Apple::getWeight));
        logger.info("apples : " + apples);

        // then
        Assert.assertEquals(Integer.valueOf(100), apples.get(0).getWeight());
        Assert.assertEquals(Integer.valueOf(200), apples.get(apples.size() - 1).getWeight());
    }
}
