package studygroup.daybreak.java8inaction.chap3.yuna;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.DoubleFunction;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * author : jihye choi
 */
public class ChapterThree {

    private static final Logger log = LoggerFactory.getLogger(ChapterThree.class);
    private static final List<Apple> inventory = Arrays.asList(new Apple(80, "green"), new Apple(155, "green"), new Apple(120, "red"));

    public static void test1(){
        Comparator<Apple> c = Comparator.comparing(Apple::getWeight);
//        inventory.sort(c);
//        log.debug(inventory.toString());

        Predicate<Apple> redApple = (Apple a) -> a.getColor().equalsIgnoreCase("red");
//        redApple.negate();
        Predicate<Apple> redAndHeavyAppleOrGree = redApple.and(a -> a.getWeight() > 150).or(a -> "green".equalsIgnoreCase(a.getColor()));

        log.debug(filterApples(inventory, redAndHeavyAppleOrGree).toString());
    }

    public static void test2() {

        Function<Integer, Integer> f = x -> x + 1;
        Function<Integer, Integer> g = x -> x * 2;
        Function<Integer, Integer> h = f.andThen(g);
        Function<Integer, Integer> i = f.compose(g);
        int result = h.apply(1);
        log.debug("result h : {}", result);

        result = i.apply(1);
        log.debug("result i : {}", result);
    }

    public static void test3() {
        Function<String, String> addHeader = Letter::addHeader;
        Function<String, String> transformationPipeline = addHeader.andThen(Letter::checkSpelling).andThen(Letter::addFooter);
        Function<String, String> transformationPipeline2 = addHeader.andThen(Letter::addFooter);

        String result = transformationPipeline.apply("hahahahahahahahah omg labda asdasde2345 ");
        log.debug("result transformationPipeline1 : {}", result);

        result = transformationPipeline2.apply(" omg omg omg ");
        log.debug("result transformationPipeline1 : {}", result);

    }

    public static void test4() {
        double result = integrate(x -> x + 10, 3, 5);
        log.debug("result : {}", result);
    }

    public static List<Apple> filterApples(List<Apple> inventory, Predicate<Apple> p) {
        List<Apple> result = new ArrayList<>();
        for(Apple apple : inventory) {
            if(p.test(apple)) {
                result.add(apple);
            }
        }

        return result;
    }

    public static double integrate(DoubleFunction<Double> f, double a, double b) {
        return (f.apply(a) + f.apply(b)) * (b-a) / 2.0;
    }

}
