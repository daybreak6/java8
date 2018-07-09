package studygroup.daybreak.java8inaction.chap2.langve;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import studygroup.daybreak.java8inaction.util.AppleUtils;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class FilteringTest {

    Logger logger = LoggerFactory.getLogger(FilteringTest.class);

    @Test
    public void filterGreenApples_1번째시도() {
        // given
        List<Apple> apples = AppleUtils.getApples();

        // when
        List<Apple> greenApples = FilteringApples.fileterGreenApples(apples);
        logger.info("greenApples : " + greenApples);

        // then
        String expected = "green";

        Assert.assertEquals(expected, greenApples.get(0).getColor());
    }

    @Test
    public void filterApplesByColor_2번째시도() {
        // given
        List<Apple> apples = AppleUtils.getApples();
        String color = "red";

        // when
        List<Apple> redApples = FilteringApples.filterApplesByColor(apples, color);
        logger.info("redApples : " + redApples);

        // then
        String expected = "red";

        Assert.assertEquals(expected, redApples.get(0).getColor());
    }

    @Test
    public void fileterApples_3번째시도() {
        // given
        List<Apple> apples = AppleUtils.getApples();
        String color = "red";
        int weight = 150;
        final boolean flagOfColor = true;
        final boolean flagOfWeight = false;

        // when
        List<Apple> redApples = FilteringApples.filterApples(apples, color, weight, flagOfColor);
        List<Apple> heabyApples = FilteringApples.filterApples(apples, color, weight, flagOfWeight);
        logger.info("redApples : " + redApples);
        logger.info("heabyApples : " + heabyApples);

        // then
        final String expectedRedColor = "red";
        Assert.assertEquals(expectedRedColor, redApples.get(0).getColor());

        final int expectedWeight = 150;
        Assert.assertTrue(expectedWeight < heabyApples.get(0).getWeight());
    }

    @Test
    public void filterApples_4번째시도() {
        // given
        List<Apple> apples = AppleUtils.getApples();
        ApplePredicate applePredicate = new AppleRedAndHeavyPredicate();

        // when
        List<Apple> actualApples = FilteringApples.filterApples(apples, applePredicate);
        logger.info("actualApples : " + actualApples);

        // then
        String expectedColor = "red";
        int expectedWeight = 150;
        Assert.assertEquals(actualApples.get(0).getColor(), expectedColor);
        Assert.assertTrue(actualApples.get(0).getWeight() > expectedWeight);
    }

    @Test
    public void filterApples_5번째시도() {
        // given
        List<Apple> apples = AppleUtils.getApples();

        // when
        List<Apple> actualApples = FilteringApples.filterApples(apples, new ApplePredicate() {
            @Override
            public boolean test(Apple apple) {
                return "red".equals(apple.getColor());
            }
        });
        logger.info("actualApples : " + actualApples);

        // then
        String expectedColor = "red";
        Assert.assertEquals(actualApples.get(0).getColor(), expectedColor);
    }

    @Test
    public void filterApples_6번째시도() {
        // given
        List<Apple> apples = AppleUtils.getApples();

        // when
        List<Apple> actualApples = FilteringApples.filterApples(apples, (Apple apple) -> "red".equals(apple.getColor()));
        logger.info("actualApples : " + actualApples);

        // then
        String expectedColor = "red";
        Assert.assertEquals(actualApples.get(0).getColor(), expectedColor);
    }

    @Test
    public void filter_7번재시도() {
        // given
        List<Apple> apples = AppleUtils.getApples();
        List<Integer> numbers = Arrays.asList(1, 2, 3);

        // when
        List<Apple> actualApples = Filtering.filter(apples, (Apple apple) -> "red".equals(apple.getColor()));
        logger.info("actualApples : " + actualApples);

        List<Integer> actualNumbers = Filtering.filter(numbers, (Integer i) -> i % 2 == 0);
        logger.info("actualNumbers : " + actualNumbers);

        // then
        String expectedColor = "red";
        Assert.assertEquals(actualApples.get(0).getColor(), expectedColor);

        Integer expectedNumber = 2;
        Assert.assertEquals(actualNumbers.get(0), expectedNumber);
    }

    @Test
    public void compare_익명클래스() {
        // given
        List<Apple> apples = AppleUtils.getApples();

        // when
        apples.sort(new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                return o1.getWeight().compareTo(o2.getWeight());
            }
        });

        // then
        Assert.assertEquals(Integer.valueOf(100), apples.get(0).getWeight());
        Assert.assertEquals(Integer.valueOf(200), apples.get(apples.size() - 1).getWeight());
    }

    @Test
    public void compare_람다식() {
        // given
        List<Apple> apples = AppleUtils.getApples();

        // when
        apples.sort((Apple apple1, Apple apple2) -> apple1.getWeight().compareTo(apple2.getWeight()));

        // then
        Assert.assertEquals(Integer.valueOf(100), apples.get(0).getWeight());
        Assert.assertEquals(Integer.valueOf(200), apples.get(apples.size() - 1).getWeight());
    }

    @Test
    public void runnable_익명클래스() {
        try {
            // given
            List<Apple> apples = AppleUtils.getApples();
            for (Apple apple : apples) {

                // when
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        logger.info(String.format("이 사과는 %s색입니다.", apple.getColor()));
                    }
                });

                thread.run();
            }
        } catch (Exception e) {
            // then
            Assert.fail();
        }

    }

    @Test
    public void runnable_람다식() {
        try {
            // given
            List<Apple> apples = AppleUtils.getApples();
            for (Apple apple : apples) {

                // when
                Thread thread = new Thread(() -> logger.info(String.format("이 사과는 %s색입니다.", apple.getColor())));

                thread.run();
            }
        } catch (Exception e) {
            // then
            Assert.fail();
        }
    }

}
