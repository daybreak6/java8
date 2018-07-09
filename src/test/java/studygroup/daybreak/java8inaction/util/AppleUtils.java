package studygroup.daybreak.java8inaction.util;

import studygroup.daybreak.java8inaction.chap2.langve.Apple;

import java.util.ArrayList;
import java.util.List;

public class AppleUtils {
    public static List<Apple> getApples() {
        List<Apple> apples = new ArrayList<>();
        apples.add(new Apple("red", 200));
        apples.add(new Apple("green", 100));
        apples.add(new Apple("green1", 100));
        apples.add(new Apple("green2", 100));
        apples.add(new Apple("green3", 100));
        apples.add(new Apple("green4", 100));
        apples.add(new Apple("green5", 100));
        apples.add(new Apple("green6", 100));
        apples.add(new Apple("green7", 100));
        apples.add(new Apple("green8", 100));
        apples.add(new Apple("green9", 100));
        return apples;
    }
}
