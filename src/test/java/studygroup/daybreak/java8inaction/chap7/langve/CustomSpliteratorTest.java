package studygroup.daybreak.java8inaction.chap7.langve;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Spliterator;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class CustomSpliteratorTest {

    private static final Logger logger = LoggerFactory.getLogger(CustomSpliteratorTest.class);
    private final String SENTENCE = " Nel   mezzo del cammin    di  nostra   vita " +
            "mi    ritrovai   in una   selva oscura" +
            " ch    la    dritta via era   smarrita ";

    @Test
    public void countWordsIteratively() {
        // given

        // when
        int actual = CustomSpliterator.countWordsIteratively(SENTENCE);
        logger.info("Found " + actual + " words");

        // then
        Assert.assertEquals(19, actual);
    }

    @Test
    public void countWords() {
        // given
        Stream<Character> stream = IntStream.range(0, SENTENCE.length())
                .mapToObj(SENTENCE::charAt);

        // when
        int actual = CustomSpliterator.countWords(stream);
        logger.info("Found " + actual + " words");

        // then
        Assert.assertEquals(19, actual);
    }

    @Test
    public void countWords_parallel() {
        // given
        Stream<Character> stream = IntStream.range(0, SENTENCE.length())
                .mapToObj(SENTENCE::charAt);

        // when
        int actual = CustomSpliterator.countWords(stream.parallel());
        logger.info("Found " + actual + " words");

        // then
        Assert.assertEquals(25, actual); // 19를 기대했겠지만 25가 나온다.
    }

    @Test
    public void countWords_parallelWithWordCounterSpliterator() {
        // given
        Spliterator<Character> spliterator = new WordCounterSpliterator(SENTENCE);
        Stream<Character> stream = StreamSupport.stream(spliterator, true);

        // when
        int actual = CustomSpliterator.countWords(stream.parallel());
        logger.info("Found " + actual + " words");

        // then
        Assert.assertEquals(19, actual); // 19가 나온다.
    }
}
