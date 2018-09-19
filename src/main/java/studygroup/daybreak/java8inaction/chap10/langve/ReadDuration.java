package studygroup.daybreak.java8inaction.chap10.langve;

import java.util.Optional;
import java.util.Properties;

public class ReadDuration {
    public static int readDuration(Properties props, String name) {
        String value = props.getProperty(name);

        if (value != null) {
            try {
                int i = Integer.parseInt(value);
                if (i > 0) {
                    return i;
                }
            } catch (NumberFormatException nfe) {

            }
        }

        return 0;
    }

    public static int readDurationInModernJava(Properties props, String name) {
        return Optional.ofNullable(props.getProperty(name))
                .flatMap(ReadDuration::stringToInt)
                .filter(i -> i > 0)
                .orElse(0);
    }

    public static Optional<Integer> stringToInt(String s) {
        try {
            return Optional.of(Integer.valueOf(s));
        } catch (NumberFormatException nfe) {
            return Optional.empty();
        }
    }
}
