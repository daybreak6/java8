package studygroup.daybreak.java8inaction.chap5.langve;

import java.util.List;
import java.util.stream.Collectors;

public class StreamMapping {
    public static List<Integer> mapping(List<Integer> intList) {
        return intList.stream()
                .map(integer -> integer * integer)
                .collect(Collectors.toList());
    }

    public static List<int[]> flatMapping(List<Integer> list1, List<Integer> list2) {
        return list1.stream()
                .flatMap(integer1 -> list2.stream()
                        .map(integer2 -> new int[]{integer1, integer2})
                )
                .collect(Collectors.toList());
    }
}
