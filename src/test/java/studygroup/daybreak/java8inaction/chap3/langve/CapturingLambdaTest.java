package studygroup.daybreak.java8inaction.chap3.langve;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CapturingLambdaTest {
    private static final Logger logger = LoggerFactory.getLogger(CapturingLambdaTest.class);

    @Test
    public void test() {
        // given
        int portNumber = 80;

        // when
        Runnable runnable = () -> {
            //portNumber++; // error!
            logger.info("portNumber : " + portNumber);
        };
        //portNumber++; // error!

        // then
        runnable.run();
    }
}
