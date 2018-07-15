package studygroup.daybreak.java8inaction.chap3.hwanho;

import java.util.ArrayList;
import java.util.List;

public class PredicateEx {

	@FunctionalInterface
	public interface Predicate<T> {
		boolean test(T t);
	}
	
	public static <T> List<T> filter(List<T> list, Predicate<T> p) {
		List<T> results = new ArrayList<>();
		for(T s: list) {
			if(p.test(s)) {
				results.add(s);
			}
		}
		return results;
	}
	
	public static void main(String ... args) {
		List<String> listOfStrings = new ArrayList<>();
		listOfStrings.add("a");
		listOfStrings.add("b");
		
		Predicate<String> nonEmptyStringPredicate = (String s) -> !s.isEmpty();
		List<String> nonEmpty = filter(listOfStrings, (String s) -> !s.isEmpty());
		
		for(String list : nonEmpty) {
			System.out.println(list);
		}
	}
}
