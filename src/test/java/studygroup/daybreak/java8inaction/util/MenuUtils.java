package studygroup.daybreak.java8inaction.util;

import java.util.Arrays;
import java.util.List;

import studygroup.daybreak.java8inaction.chap5.hwanho.Dish;

public class MenuUtils {
	public static List<Dish> getMenus() {
		List<Dish> menu = Arrays.asList(
				new Dish("pork", false, 800, Dish.Type.MEAT),
				new Dish("beef", false, 700, Dish.Type.MEAT),
				new Dish("chicken", false, 400, Dish.Type.MEAT),
				new Dish("frech fries", true, 530, Dish.Type.MEAT),
				new Dish("rice", true, 350, Dish.Type.OTFHER),
				new Dish("season fruit", true, 120, Dish.Type.OTFHER),
				new Dish("pizza", true, 550, Dish.Type.OTFHER),
				new Dish("prawns", false, 300, Dish.Type.FISH),
				new Dish("salmon", false, 450, Dish.Type.FISH));
		return menu;
	}
}
