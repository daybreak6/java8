package studygroup.daybreak.java8inaction.chap2.yuna;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import studygroup.daybreak.java8inaction.chap3.yuna.Apple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

/**
 * author : jihye choi
 */
public class ChapterTwo {

    private static final Logger log = LoggerFactory.getLogger(ChapterTwo.class);

    private static final List<Apple> inventory = Arrays.asList(new Apple(80, "green"), new Apple(155, "green"), new Apple(120, "red"));
    private static final List<Integer> nums = Arrays.asList(5, 10, 23, 444, 12345, 123);

    public static void main(String[] args) {

//        test1();
//        test2();
//        test3();
//        test4();
//        test5();
        test6();
        test7();
    }

    public static void prettyPrintApple(List<Apple> inventory, AppleFormatter formatter) {
        for(Apple apple : inventory) {
            log.debug(formatter.accept(apple));
        }
    }

    public static void test1() {
        List<Apple> inventory = new ArrayList<>();
        Apple apple = new Apple();
        apple.setWeight(130);
        apple.setColor("red");
        inventory.add(apple);
        apple = new Apple();
        apple.setWeight(150);
        apple.setColor("green");
        inventory.add(apple);

        prettyPrintApple(inventory, new AppleFancyFormatter());
        prettyPrintApple(inventory, new AppleSimpleFormatter());

    }

    public static List<Apple> filterApples(List<Apple> inventory, ApplePredicate p) {
        List<Apple> result = new ArrayList<>();
        for(Apple apple : inventory) {
            if(p.test(apple)) {
                result.add(apple);
            }
        }

        return result;
    }

    public static void test2() {

        List<Apple> heavyApples = filterApples(inventory, new AppleHeavyWeightPredicate());
        List<Apple> greenApples = filterApples(inventory, new AppleGreenColorPredicate());

        log.debug("heavyApples : {} \n greenApples : {} ", heavyApples.toString(), greenApples.toString());

    }

    public static void test3() {
        List<Apple> result = filterApples(inventory, (Apple apple) -> "red".equalsIgnoreCase(apple.getColor()));
        log.debug("result : {}", result.toString());
    }

    public static void test4() {
        List<Apple> redApples = filter(inventory, (Apple apple) -> "red".equalsIgnoreCase(apple.getColor()));
        List<Integer> evenNumbers = filter(nums, (Integer i) -> i % 2 == 0);
        log.debug("redApples : {} , evenNumbers : {} ", redApples.toString(), evenNumbers.toString());
    }

    public static void test5() {
        inventory.sort(new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                return (o1.getWeight() + "").compareTo( o2.getWeight() + "");
            }
        });

        log.debug(inventory.toString());
    }

    public static void test6() {
        List<String> listOfStrings = Arrays.asList("", "1234", "test", "");
        Predicate<String> nonEmptyStringPredication = (String s) -> !s.isEmpty();
        List<String> nonEmpty = filter(listOfStrings, nonEmptyStringPredication);
        log.debug("nonEmpty : {}", nonEmpty.toString());
    }

    public static void test7() {
        forEach(Arrays.asList(1, 2, 3, 4, 5, 6), (Integer i) -> System.out.println(i));
    }

    public static <T> List<T> filter(List<T> list, Predicate<T> p) {
        List<T> result = new ArrayList<>();
        for(T e: list) {
            if(p.test(e)) {
                result.add(e);
            }
        }
        return result;
    }

    public static <T> void forEach(List<T> list, Consumer<T> c) {
        for(T i: list) {
            c.accept(i);
        }
    }
}


interface AppleFormatter {
    String accept(Apple a);
}

interface ApplePredicate {
    boolean test(Apple apple);
}

/*

@FunctionalInterface
interface Predicate<T> {
    boolean test(T t);
}
*/

@FunctionalInterface
interface Consumer<T> {
    void accept(T t);
}
/*

interface Comparator<T> {
    int compare(T o1, T o2);
}
*/

class AppleFancyFormatter implements AppleFormatter {

    @Override
    public String accept(Apple apple) {
        String charr = apple.getWeight() > 150 ? "heavy" : "light";
        return String.format("A %s %s apple", charr, apple.getWeight());

    }
}

class AppleSimpleFormatter implements AppleFormatter {

    @Override
    public String accept(Apple apple) {
        return String.format("An apple of %s", apple.getWeight());
    }
}

class AppleHeavyWeightPredicate implements ApplePredicate {

    @Override
    public boolean test(Apple apple) {
        return apple.getWeight() > 150;
    }
}

class AppleGreenColorPredicate implements ApplePredicate {

    @Override
    public boolean test(Apple apple) {
        return "green".equalsIgnoreCase(apple.getColor());
    }
}