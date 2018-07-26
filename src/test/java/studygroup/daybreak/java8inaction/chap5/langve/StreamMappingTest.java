package studygroup.daybreak.java8inaction.chap5.langve;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

public class StreamMappingTest {
    private static final Logger logger = LoggerFactory.getLogger(StreamMappingTest.class);

    @Test
    public void mapping_퀴즈5_2_1() {
        // given
        List<Integer> intList = Arrays.asList(1, 2, 3, 4, 5);

        // when
        List<Integer> actual = StreamMapping.mapping(intList);
        logger.info("actual : " + actual);

        // then
        Assert.assertEquals(1, actual.get(0).intValue());
        Assert.assertEquals(4, actual.get(1).intValue());
        Assert.assertEquals(9, actual.get(2).intValue());
        Assert.assertEquals(16, actual.get(3).intValue());
        Assert.assertEquals(25, actual.get(4).intValue());
    }

    @Test
    public void flatMapping_퀴즈5_2_2() {
        // given
        List<Integer> list1 = Arrays.asList(1, 2, 3);
        List<Integer> list2 = Arrays.asList(3, 4);

        // when
        List<int[]> actual = StreamMapping.flatMapping(list1, list2);
        logger.info("actual : " + actual);

        // then
        Assert.assertArrayEquals(new int[]{1, 3}, actual.get(0));
        Assert.assertArrayEquals(new int[]{1, 4}, actual.get(1));
        Assert.assertArrayEquals(new int[]{2, 3}, actual.get(2));
        Assert.assertArrayEquals(new int[]{2, 4}, actual.get(3));
        Assert.assertArrayEquals(new int[]{3, 3}, actual.get(4));
        Assert.assertArrayEquals(new int[]{3, 4}, actual.get(5));
    }
}
