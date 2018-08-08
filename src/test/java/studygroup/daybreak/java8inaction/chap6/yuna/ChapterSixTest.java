package studygroup.daybreak.java8inaction.chap6.yuna;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertEquals;

public class ChapterSixTest {

    Logger logger = LoggerFactory.getLogger(ChapterSixTest.class);
    @Test
    public void 테스트일() {
        assertEquals("pork", ChapterSix.test1());
    }

    @Test
    public void 테스트2() {
        assertEquals(4300, ChapterSix.test2());
    }

    @Test
    public void 테스트삼() {
        ChapterSix.test3();
    }

    @Test
    public void 리듀싱테스트() {
        ChapterSix.reducingTest();
    }

    @Test
    public void 그루핑테스트() {
        ChapterSix.groupingTest();
    }

    @Test
    public void 파티셔닝테스트() {
        ChapterSix.partitioningTest();
    }

    @Test
    public void 소수테스트() {
        logger.debug("isPrime : {}", ChapterSix.partitionPrimes(30));
        logger.debug("isPrime custom : {}", ChapterSix.partitionPrimesWithCustomCollector(100));
    }

    @Test
    public void 스피드테스트() {
        logger.debug("partitioning(partitionPrimes) done in : [ {} ] msecs", ChapterSix.collectorHarness(ChapterSix::partitionPrimes));
        logger.debug("partitioning(partitionPrimesWithCustomCollector) done in : [ {} ] msecs", ChapterSix.collectorHarness(ChapterSix::partitionPrimesWithCustomCollector));
    }
}
