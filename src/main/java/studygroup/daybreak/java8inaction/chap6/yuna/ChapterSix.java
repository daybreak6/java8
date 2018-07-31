package studygroup.daybreak.java8inaction.chap6.yuna;

import studygroup.daybreak.java8inaction.chap5.yuna.Dish;

import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class ChapterSix {

    private static final Logger logger = Logger.getLogger(ChapterSix.class.getName());

    public static String test1() {

        Comparator<Dish> dishCaloriesComparator = Comparator.comparingInt(Dish::getCalories);
        Optional<Dish> mostCalorieDish = Dish.menu.stream().collect(Collectors.maxBy(dishCaloriesComparator));
        logger.info("mostCalorieDish 11 : {}" + mostCalorieDish.get().getName());

        mostCalorieDish = Dish.menu.stream().max(dishCaloriesComparator);
        logger.info("mostCalorieDish 22 : {}" + ( mostCalorieDish.isPresent() ? mostCalorieDish.get().getName() : ""));

        return mostCalorieDish.isPresent() ? mostCalorieDish.get().getName() : "";
    }

    public static int test2() {

        int totalCalories = Dish.menu.stream().collect(Collectors.summingInt(Dish::getCalories));
        logger.info("totalCalories 11 : {}" + totalCalories);

        totalCalories = Dish.menu.stream().mapToInt(Dish::getCalories).sum();
        logger.info("totalCalories 22 : {}" + totalCalories);

        double avgCalories = Dish.menu.stream().collect(Collectors.averagingDouble(Dish::getCalories));
        logger.info("avgCalories : {}" + avgCalories);


        IntSummaryStatistics menuStatistics = Dish.menu.stream().collect(Collectors.summarizingInt(Dish::getCalories));
        logger.info("menuStatistics : {}" + menuStatistics.toString());
        logger.info("menu count : {}" + menuStatistics.getCount());

        return totalCalories;

    }

    public static void test3() {
        String shortMenu = Dish.menu.stream().map(Dish::getName).collect(Collectors.joining(", "));
        logger.info("shortMenu : {}" + shortMenu);
    }

    public static void reducingTest() {

        int totalCalories = Dish.menu.stream().collect(Collectors.reducing(0, Dish::getCalories, (i, j) -> i + j));
        logger.info("totalCalories 11 : {}" + totalCalories);

        totalCalories = Dish.menu.stream().map(Dish::getCalories).reduce(0, (i, j) -> i + j);
        logger.info("totalCalories 22 : {}" + totalCalories);

        totalCalories = Dish.menu.stream().map(Dish::getCalories).reduce(Integer::sum).get();
        logger.info("totalCalories 33 : {}" + totalCalories);

        totalCalories = Dish.menu.stream().collect(Collectors.reducing(0, Dish::getCalories, Integer::sum));
        logger.info("totalCalories 44 : {}" + totalCalories);

        totalCalories = Dish.menu.stream().mapToInt(Dish::getCalories).sum();
        logger.info("totalCalories 55 : {}" + totalCalories);

        Optional<Dish> mostCalorieDish = Dish.menu.stream().collect(Collectors.reducing((d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2));
        logger.info("mostCalorieDish 11 - name : " + mostCalorieDish.get().getName() + ", calorie : " + mostCalorieDish.get().getCalories());

        mostCalorieDish = Dish.menu.stream().reduce((d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2);
        logger.info("mostCalorieDish 22 - name : " + mostCalorieDish.get().getName() + ", calorie : " + mostCalorieDish.get().getCalories());

    }

    public static void groupingTest() {
        Map<Dish.Type, List<Dish>> dishesByType = Dish.menu.stream().collect(Collectors.groupingBy(Dish::getType));
        logger.info("dishesByType : {}" + dishesByType.toString());
    }

    public static <T> Collector<T, ? ,Long> counting() {
        return Collectors.reducing(0L, e-> 1L, Long::sum);
    }

}
