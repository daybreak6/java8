package studygroup.daybreak.java8inaction.chap3.yuna;


/**
 * author : jihye choi
 */
public class Letter {

    public static String addHeader(String text) {
        return String.format("From Raoul, Mario and Alan : %s", text);
    }

    public static String addFooter(String text) {
        return String.format("%s Kind regards", text);
    }

    public static String checkSpelling(String text) {
        return text.replaceAll("labda", "lambda");
    }
}
