package com.yuna.inaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import studygroup.daybreak.java8inaction.chap5.yuna.Dish;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ChapterSix {

    private static final Logger logger = LoggerFactory.getLogger(ChapterSix.class);

    public static String test1() {

        Comparator<Dish> dishCaloriesComparator = Comparator.comparingInt(Dish::getCalories);
        Optional<Dish> mostCalorieDish = Dish.menu.stream().collect(Collectors.maxBy(dishCaloriesComparator));
        logger.debug("mostCalorieDish 11 : {}", mostCalorieDish.get().getName());

        mostCalorieDish = Dish.menu.stream().max(dishCaloriesComparator);
        logger.debug("mostCalorieDish 22 : {}", mostCalorieDish.isPresent() ? mostCalorieDish.get().getName() : "");

        return mostCalorieDish.isPresent() ? mostCalorieDish.get().getName() : "";
    }

    public static int test2() {

        int totalCalories = Dish.menu.stream().collect(Collectors.summingInt(Dish::getCalories));
        logger.debug("totalCalories 11 : {}", totalCalories);

        totalCalories = Dish.menu.stream().mapToInt(Dish::getCalories).sum();
        logger.debug("totalCalories 22 : {}", totalCalories);

        double avgCalories = Dish.menu.stream().collect(Collectors.averagingDouble(Dish::getCalories));
        logger.debug("avgCalories : {}", avgCalories);


        IntSummaryStatistics menuStatistics = Dish.menu.stream().collect(Collectors.summarizingInt(Dish::getCalories));
        logger.debug("menuStatistics : {}", menuStatistics.toString());
        logger.debug("menu count : {}", menuStatistics.getCount());

        return totalCalories;

    }

    public static void test3() {
        String shortMenu = Dish.menu.stream().map(Dish::getName).collect(Collectors.joining(", "));
        logger.debug("shortMenu : {}", shortMenu);
    }

    public static void reducingTest() {

        int totalCalories = Dish.menu.stream().collect(Collectors.reducing(0, Dish::getCalories, (i, j) -> i + j));
        logger.debug("totalCalories 11 : {}", totalCalories);

        totalCalories = Dish.menu.stream().map(Dish::getCalories).reduce(0, (i, j) -> i + j);
        logger.debug("totalCalories 22 : {}", totalCalories);

        totalCalories = Dish.menu.stream().map(Dish::getCalories).reduce(Integer::sum).get();
        logger.debug("totalCalories 33 : {}", totalCalories);

        totalCalories = Dish.menu.stream().collect(Collectors.reducing(0, Dish::getCalories, Integer::sum));
        logger.debug("totalCalories 44 : {}", totalCalories);

        totalCalories = Dish.menu.stream().mapToInt(Dish::getCalories).sum();
        logger.debug("totalCalories 55 : {}", totalCalories);

        Optional<Dish> mostCalorieDish = Dish.menu.stream().collect(Collectors.reducing((d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2));
        logger.debug("mostCalorieDish 11 - name : {}, calorie : {}", mostCalorieDish.get().getName(), mostCalorieDish.get().getCalories());

        mostCalorieDish = Dish.menu.stream().reduce((d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2);
        logger.debug("mostCalorieDish 22 - name : {}, calorie : {}", mostCalorieDish.get().getName(), mostCalorieDish.get().getCalories());

    }

    enum CaloricLevel { DIET, NORMAL, FAT }

    public static void groupingTest() {
        Map<Dish.Type, List<Dish>> dishesByType = Dish.menu.stream().collect(Collectors.groupingBy(Dish::getType));
        logger.debug("dishesByType : {}", dishesByType.toString());

        Map<CaloricLevel, List<Dish>> dishesByCaloricLevel = Dish.menu.stream().collect(
                Collectors.groupingBy(dish -> {
                    if (dish.getCalories() <= 400) {
                        return CaloricLevel.DIET;
                    } else if (dish.getCalories() <= 700) {
                        return CaloricLevel.NORMAL;
                    } else {
                        return CaloricLevel.FAT;
                    }
                })
        );

        logger.debug("dishesByCaloricLevel : {}", dishesByCaloricLevel);

        Map<Dish.Type, Map<CaloricLevel, List<Dish>>> dishesByTypeCaloricLevel = Dish.menu.stream().collect(Collectors.groupingBy(Dish::getType, Collectors.groupingBy(dish -> {
            if (dish.getCalories() <= 400) {
                return CaloricLevel.DIET;
            } else if (dish.getCalories() <= 700) {
                return CaloricLevel.NORMAL;
            } else {
                return CaloricLevel.FAT;
            }
        })));
        logger.debug("dishesByTypeCaloricLevel : {}", dishesByTypeCaloricLevel);

        Map<Dish.Type, Long> typesCount = Dish.menu.stream().collect(Collectors.groupingBy(Dish::getType, counting()));
        logger.debug("typesCount : {}", typesCount);

        Map<Dish.Type, Optional<Dish>> mostCaloricByType = Dish.menu.stream().collect(Collectors.groupingBy(Dish::getType, Collectors.maxBy(Comparator.comparingInt(Dish::getCalories))));
        logger.debug("mostCaloricByType : {}", mostCaloricByType);

        Map<Dish.Type, Dish> mostCaloricByType2 = Dish.menu.stream().collect(Collectors.groupingBy(Dish::getType, Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparingInt(Dish::getCalories)), Optional::get)));
        logger.debug("mostCaloricByType2 : {}", mostCaloricByType2);

        Map<Dish.Type, Integer> totalCaloriesByType = Dish.menu.stream().collect(Collectors.groupingBy(Dish::getType, Collectors.summingInt(Dish::getCalories)));
        logger.debug("totalCaloriesByType : {}", totalCaloriesByType);

        Map<Dish.Type, Set<CaloricLevel>> caloricLevelsByType = Dish.menu.stream().collect(Collectors.groupingBy(Dish::getType, Collectors.mapping(
                dish -> {
                    if (dish.getCalories() <= 400) {
                        return CaloricLevel.DIET;
                    } else if (dish.getCalories() <= 700) {
                        return CaloricLevel.NORMAL;
                    } else {
                        return CaloricLevel.FAT;
                    }
                }, Collectors.toSet()
        )));
        logger.debug("caloricLevelsByType : {}", caloricLevelsByType);

        caloricLevelsByType = Dish.menu.stream().collect(Collectors.groupingBy(Dish::getType, Collectors.mapping(
                dish -> {
                    if (dish.getCalories() <= 400) {
                        return CaloricLevel.DIET;
                    } else if (dish.getCalories() <= 700) {
                        return CaloricLevel.NORMAL;
                    } else {
                        return CaloricLevel.FAT;
                    }
                }, Collectors.toCollection(HashSet::new)
        )));
        logger.debug("caloricLevelsByType 22 hashSet new : {}", caloricLevelsByType);
    }

    public static void partitioningTest() {
        Map<Boolean, List<Dish>> partitionedMenu = Dish.menu.stream().collect(Collectors.partitioningBy(Dish::isVegetarian));
        logger.debug("partitionedMenu : {}", partitionedMenu);

        List<Dish> vegetarianDishes = partitionedMenu.get(true);
        logger.debug("vegetarianDishes : {}", vegetarianDishes);

        vegetarianDishes = Dish.menu.stream().filter(Dish::isVegetarian).collect(Collectors.toList());
        logger.debug("vegetarianDishes 22 : {}", vegetarianDishes);

        Map<Boolean, Map<Dish.Type, List<Dish>>> vegetarianDishesByType = Dish.menu.stream().collect(Collectors.partitioningBy(Dish::isVegetarian, Collectors.groupingBy(Dish::getType)));
        logger.debug("vegetarianDishesByType : {}", vegetarianDishesByType);

        Map<Boolean, Dish> mostCaloricPartitionedByVegetarian = Dish.menu.stream().collect(Collectors.partitioningBy(Dish::isVegetarian, Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparingInt(Dish::getCalories)), Optional::get)));
        logger.debug("mostCaloricPartitionedByVegetarian : {}", mostCaloricPartitionedByVegetarian);

        Map<Boolean, Map<Boolean, List<Dish>>> quiz1 = Dish.menu.stream().collect(Collectors.partitioningBy(Dish::isVegetarian, Collectors.partitioningBy(d -> d.getCalories() > 500)));
        logger.debug("quiz1 : {}", quiz1);

        // quiz2 쓸 수 있게 수정해보기
        Map<Boolean, Dish> quiz2 = Dish.menu.stream().collect(Collectors.partitioningBy(Dish::isVegetarian, Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparingInt(Dish::getCalories)), Optional::get)));
        logger.debug("quiz2 : {}", quiz2);

        Map<Boolean, Long> quiz3 = Dish.menu.stream().collect(Collectors.partitioningBy(Dish::isVegetarian, counting()));
        logger.debug("quiz3 : {}", quiz3);
    }


    public static <T> Collector<T, ? ,Long> counting() {
        return Collectors.reducing(0L, e-> 1L, Long::sum);
    }

    // 정수인지 소수인지
    public static boolean isPrime(int candidate) {
//        return IntStream.range(2, candidate).noneMatch( i -> candidate % i == 0);

        // 주어진 수의 제곱근 이하로 제한
        int candidateRoot = (int) Math.sqrt((double) candidate);
        return IntStream.rangeClosed(2, candidateRoot).noneMatch( i -> candidate % i == 0);
    }

    //    isPrime을 predicate로 이용한 소수, 비소수 구분
    public static Map<Boolean, List<Integer>> partitionPrimes(int n) {
        return IntStream.rangeClosed(2, n).boxed().collect(Collectors.partitioningBy(candidate -> isPrime(candidate)));
    }

    public static Map<Boolean, List<Integer>> partitionPrimesWithCustomCollector(int n) {
        return IntStream.rangeClosed(2, n).boxed().collect(new PrimeNumbersCollector());
    }

    //    전체 stream을 처리한 후 결과를 반환하는 filter 대신 대상의 제곱보다 큰 소수를 찾으면 검사를 중단하는
    public static <A> List<A> takeWhile(List<A> list, Predicate<A> p) {
        int i = 0;
        for(A item: list) {
            if(!p.test(item)) {
                return list.subList(0, i);
            }
            i++;
        }
        return list;
    }

    public static boolean isPrime(List<Integer> primes, int candidate) {
        int candidateRoot = (int) Math.sqrt((double) candidate);
        return takeWhile(primes, i -> i <= candidateRoot).stream().noneMatch(p -> candidate % p == 0);
    }

    public static long collectorHarness(Consumer<Integer> primePartitioner) {
        long fastest = Long.MAX_VALUE;
        for(int i = 0; i < 10; i++) {
            long start = System.nanoTime();
            primePartitioner.accept(1_000_000);
            long duration = (System.nanoTime() - start) / 1_000_000;
            if(duration < fastest) {
                fastest = duration;
            }
        }
        return fastest;
    }
}
