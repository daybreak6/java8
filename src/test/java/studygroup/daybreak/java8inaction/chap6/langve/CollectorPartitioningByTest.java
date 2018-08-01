package studygroup.daybreak.java8inaction.chap6.langve;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import studygroup.daybreak.java8inaction.chap5.langve.Dish;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.partitioningBy;

public class CollectorPartitioningByTest {
    private static final Logger logger = LoggerFactory.getLogger(CollectorPartitioningByTest.class);

    @Test
    public void partitioningBy_quiz_6_2() {
        // given

        // when
        Map<Boolean, Map<Boolean, List<Dish>>> map1 = Dish.menu.stream().collect(partitioningBy(Dish::isVegetarian,
                partitioningBy(d -> d.getCalories() > 500)));

        //Dish.menu.stream().collect(partitioningBy(Dish::isVegetarian,
        //        partitioningBy(Dish::getType))); // compile error - partitioningBy는 프레디케이트를 요구하므로 요건에 맞지 않다.

        Map<Boolean, Long> map2 = Dish.menu.stream().collect(partitioningBy(Dish::isVegetarian, counting()));

        // then
        logger.info(map1.toString());
        logger.info(map2.toString());
    }
}
