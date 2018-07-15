package studygroup.daybreak.java8inaction.chap3.hwanho;

import java.util.Arrays;
import java.util.List;

public class ConsumerEx {

	@FunctionalInterface
	public interface Consumer<T> {
		void accept(T t);
	}
	
	public static <T> void forEach(List<T> list, Consumer<T> c) {
		for(T i: list) {
			c.accept(i);
		}
	}
	
	public static void main(String ... args) {
		
		forEach(Arrays.asList(1, 2, 3, 4, 5), (Integer i) -> System.out.println(i));
	}
}
