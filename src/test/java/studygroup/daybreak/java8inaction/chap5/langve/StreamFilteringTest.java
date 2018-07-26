package studygroup.daybreak.java8inaction.chap5.langve;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class StreamFilteringTest {
    private static final Logger logger = LoggerFactory.getLogger(StreamFilteringTest.class);

    @Test
    public void filtering_퀴즈5_1() {
        // given
        List<Dish> menu = Dish.menu;

        // when
        List<Dish> actual = StreamFiltering.filtering(menu);
        logger.info("actual : " + actual);

        // then
        assertEquals(2, actual.size());
    }
}
