package studygroup.daybreak.java8inaction.chap3.hwanho;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FunctionEx {

	@FunctionalInterface
	public interface Function<T, R> {
		R apply(T t);
	}
	
	public static <T, R> List<R> map(List<T> list, Function<T, R> f) {
		List<R> result = new ArrayList<>();
		for(T s: list) {
			result.add(f.apply(s));
		}
		return result;
	}
	
	public static void main(String ... args) {
		List<Integer> list = map(Arrays.asList("lambdas", "in", "action"), (String s) -> s.length());
		
		for(Integer i: list) {
			System.out.println(i);
		}
		
	}
}
