package studygroup.daybreak.java8inaction.chap7.yuna;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * author: jihye choi
 */
public class ChapterSevenTest {

    private static final Logger logger = LoggerFactory.getLogger(ChapterSevenTest.class);

    @Test
    public void 병렬테스트1() {
//        logger.debug("sequentialSum : {}", ChapterSeven.sequentialSum(30));
//        logger.debug("parallelSum : {}", ChapterSeven.parallelSum(30));

//        logger.debug("sequentialSum done in : [ {} ] msecs", ChapterSix.collectorHarness(ChapterSeven::sequentialSum));
//        logger.debug("parallelSum done in : [ {} ] msecs", ChapterSix.collectorHarness(ChapterSeven::parallelSum));

//        logger.debug("measureSumPerf sequentialSum in [ {} ] msecs", ChapterSeven.measureSumPerf(ChapterSeven::sequentialSum, 10_000_000));
//        logger.debug("measureSumPerf parallelSum in [ {} ] msecs", ChapterSeven.measureSumPerf(ChapterSeven::parallelSum, 10_000_000));

        logger.debug("rangedSum : {}", ChapterSeven.rangedSum(30));
        logger.debug("parallelRangedSum : {}", ChapterSeven.parallelRangedSum(30));

        logger.debug("measureSumPerf rangedSum in [ {} ] msecs", ChapterSeven.measureSumPerf(ChapterSeven::rangedSum, 10_000_000));
        logger.debug("measureSumPerf parallelRangedSum in [ {} ] msecs", ChapterSeven.measureSumPerf(ChapterSeven::parallelRangedSum, 10_000_000));
    }

    @Test
    public void 병렬스레드테스트() {
        logger.debug("measureSumPerf forkJoinSum in [ {} ] msecs", ChapterSeven.measureSumPerf(ForkJoinSumCalculator::forkJoinSum, 10_000_000L));
    }

}
