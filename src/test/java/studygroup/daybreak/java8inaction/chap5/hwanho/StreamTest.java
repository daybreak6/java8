package studygroup.daybreak.java8inaction.chap5.hwanho;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Ignore;
import org.junit.Test;

import studygroup.daybreak.java8inaction.util.MenuUtils;

public class StreamTest {
	
	@Ignore
	@Test
	public void testFilter() {
		List<Dish> vegetarianMenu = MenuUtils.getMenus().stream()
				.filter(Dish::isVegetarian).collect(Collectors.toList());
		vegetarianMenu.stream().forEach(System.out::println);
	}
	
	@Ignore
	@Test
	public void testDistinct() {
		List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
		numbers.stream().filter(i -> i%2 == 0).distinct().forEach(System.out::println);
	}
	
	@Ignore
	@Test
	public void testLimit() {
		List<Dish> dishes = MenuUtils.getMenus().stream()
				.filter(d -> d.getCalories() > 300).limit(3).collect(Collectors.toList());
		dishes.stream().forEach(System.out::println);
		
	}
	
	@Ignore
	@Test
	public void testSkip() {
		List<Dish> dishes = MenuUtils.getMenus().stream()
				.filter(d -> d.getCalories() > 300).skip(2).collect(Collectors.toList());
		dishes.stream().forEach(System.out::println);
	}
	
	@Test
	public void testQuiz1() {
		List<Dish> dishes = MenuUtils.getMenus().stream()
				.filter(d -> d.getType() == Dish.Type.MEAT).limit(2).collect(Collectors.toList());
		dishes.stream().forEach(System.out::println);
	}
}
