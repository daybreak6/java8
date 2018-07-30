package studygroup.daybreak.java8inaction.chap6.prrain;

import static java.util.stream.Collectors.averagingInt;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.reducing;
import static java.util.stream.Collectors.summarizingInt;
import static studygroup.daybreak.java8inaction.chap6.prrain.Dish.menu;

import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;


public class Summarizing {
	public static void main(String[] args) {

		List<Dish> dishList = menu.stream().collect(Collectors.toList());
		System.out.println("dishList =" + dishList.toString());

		System.out.println("howmanyDished =" + howManyDishes());
		System.out.println("findMostCaloricDishUsingComparator =" + findMostCaloricDishUsingComparator());
		System.out.println("calculateAverageCalories =" + calculateAverageCalories());
		System.out.println("calculateMenuStatistics =" + calculateMenuStatistics());
		System.out.println("getShortMenu =" + getShortMenu());
		System.out.println("getShortMenuCommaSeparated =" + getShortMenuCommaSeparated());
	}

	private static long howManyDishes() {
		return menu.stream().collect(counting());
	}

	private static Dish findMostCaloricDishUsingComparator() {
		/*
		 * Comparator<Dish> dishCaloriesComparator = new Comparator<Dish>() { public int
		 * compare(Dish a1, Dish a2) { return
		 * Integer.valueOf(a1.getCalories()).compareTo(Integer.valueOf(a2.getCalories())
		 * ); } };
		 */

		// Comparator<Dish> dishCaloriesComparator = (Dish a1, Dish a2) ->
		// Integer.valueOf(a1.getCalories()).compareTo(Integer.valueOf(a2.getCalories()));
		Comparator<Dish> dishCaloriesComparator = Comparator.comparingInt(Dish::getCalories);
		BinaryOperator<Dish> moreCaloricOf = BinaryOperator.maxBy(dishCaloriesComparator);
		return menu.stream().collect(reducing(moreCaloricOf)).get();
	}

	private static Double calculateAverageCalories() {
		return menu.stream().collect(averagingInt(Dish::getCalories));
	}

	private static IntSummaryStatistics calculateMenuStatistics() {
		return menu.stream().collect(summarizingInt(Dish::getCalories));
	}

	private static String getShortMenu() {
		return menu.stream().map(Dish::getName).collect(joining());
	}

	private static String getShortMenuCommaSeparated() {
		return menu.stream().map(Dish::getName).collect(joining(", "));
	}
}
