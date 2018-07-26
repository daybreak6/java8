package studygroup.daybreak.java8inaction.chap5.langve;

import java.util.List;
import java.util.stream.Collectors;

public class StreamFiltering {

    // 퀴즈 5-1
    public static List<Dish> filtering(List<Dish> menu) {
        return menu.stream()
                .filter(dish -> dish.getType().equals(Dish.Type.MEAT))
                .limit(2)
                .collect(Collectors.toList());
    }
}
