package studygroup.daybreak.java8inaction.chap6.prrain;

import static java.util.stream.Collectors.reducing;
import static studygroup.daybreak.java8inaction.chap6.prrain.Dish.menu;

import java.util.List;
import java.util.stream.Collectors;


public class Reducing {
	public static void main(String[] args) {

		List<Integer> dishList = menu.stream().map(Dish::getCalories).collect(Collectors.toList());
		System.out.println("dishList =" + dishList.toString());

		System.out.println("Total calories in menu: " + calculateTotalCalories());
		System.out.println("Total calories in menu: " + calculateTotalCaloriesWithMethodReference());
		System.out.println("Total calories in menu: " + calculateTotalCaloriesWithoutCollectors());
		System.out.println("Total calories in menu: " + calculateTotalCaloriesUsingSum());
	}

	private static int calculateTotalCalories() {
		return menu.stream().collect(reducing(0, Dish::getCalories, (Integer i, Integer j) -> i + j));
	}

	private static int calculateTotalCaloriesWithMethodReference() {
		return menu.stream().collect(reducing(0, Dish::getCalories, Integer::sum));
	}

	private static int calculateTotalCaloriesWithoutCollectors() {
		return menu.stream().map(Dish::getCalories).reduce(Integer::sum).get();
	}

	private static int calculateTotalCaloriesUsingSum() {
		return menu.stream().mapToInt(Dish::getCalories).sum();
	}
}
