package studygroup.daybreak.java8inaction.chap2.langve;

import java.util.ArrayList;
import java.util.List;

public class FilteringApples {
    public static List<Apple> fileterGreenApples(List<Apple> apples) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : apples) {
            if ("green".equals(apple.getColor())) {
                result.add(apple);
            }
        }

        return result;
    }

    public static List<Apple> filterApplesByColor(List<Apple> apples, String color) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : apples) {
            if (color.equals(apple.getColor())) {
                result.add(apple);
            }
        }

        return result;
    }

    public static List<Apple> filterApples(List<Apple> apples, String color, int weight, boolean flag) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : apples) {
            if ((flag && color.equals(apple.getColor())) || (!flag && weight < apple.getWeight())) {
                result.add(apple);
            }
        }

        return result;
    }

    public static List<Apple> filterApples(List<Apple> apples, ApplePredicate applePredicate) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : apples) {
            if (applePredicate.test(apple)) {
                result.add(apple);
            }
        }

        return result;
    }

}
