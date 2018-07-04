package studygroup.daybreak.java8inaction.chap2.langve;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Filtering {
    public static <T> List<T> filter(List<T> list, Predicate<T> predicate) {
        List<T> result = new ArrayList<>();
        for (T t : list) {
            if (predicate.test(t)) {
                result.add(t);
            }
        }

        return result;
    }
}
