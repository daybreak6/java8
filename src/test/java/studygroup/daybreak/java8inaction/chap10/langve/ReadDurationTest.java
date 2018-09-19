package studygroup.daybreak.java8inaction.chap10.langve;

import org.junit.Assert;
import org.junit.Test;

import java.util.Properties;

public class ReadDurationTest {
    private static Properties props = new Properties();

    static {
        props.setProperty("a", "5");
        props.setProperty("b", "true");
        props.setProperty("c", "-3");
    }

    @Test
    public void readDuration() {
        Assert.assertEquals(5, ReadDuration.readDuration(props, "a"));
        Assert.assertEquals(0, ReadDuration.readDuration(props, "b"));
        Assert.assertEquals(0, ReadDuration.readDuration(props, "c"));
        Assert.assertEquals(0, ReadDuration.readDuration(props, "d"));
    }

    @Test
    public void readDuration_quiz_10_3() {
        Assert.assertEquals(5, ReadDuration.readDurationInModernJava(props, "a"));
        Assert.assertEquals(0, ReadDuration.readDurationInModernJava(props, "b"));
        Assert.assertEquals(0, ReadDuration.readDurationInModernJava(props, "c"));
        Assert.assertEquals(0, ReadDuration.readDurationInModernJava(props, "d"));
    }
}
