package studygroup.daybreak.java8inaction.chap6.prrain;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.maxBy;
import static java.util.stream.Collectors.partitioningBy;
import static studygroup.daybreak.java8inaction.chap6.prrain.Dish.menu;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * 분할함수 : 프레디케이트를 분류 함수로 사용하는 특수한 그룹화 기능 (맵의 키는 boolean)
 * 장점 :  분할함수가 반환하는 참, 거짓 두 가지 요소의 스트림 리스트를 모두 유지 
 * @author Administrator
 *
 */
public class Partitioning {
	public static void main(String ... args) {
        System.out.println("Dishes partitioned by vegetarian: " + partitionByVegeterian());
        System.out.println("Vegetarian Dishes by type: " + vegetarianDishesByType());
        System.out.println("Most caloric dishes by vegetarian: " + mostCaloricPartitionedByVegetarian());
    }

    private static Map<Boolean, List<Dish>> partitionByVegeterian() {
        return menu.stream().collect(partitioningBy(Dish::isVegetarian));
    }

    private static Map<Boolean, Map<Dish.Type, List<Dish>>> vegetarianDishesByType() {
        return menu.stream().collect(partitioningBy(Dish::isVegetarian, groupingBy(Dish::getType)));
    }

    private static Object mostCaloricPartitionedByVegetarian() {
        return menu.stream().collect(
                partitioningBy(Dish::isVegetarian,
                        collectingAndThen(
                                maxBy(comparingInt(Dish::getCalories)),
                                Optional::get)));
    }
}
