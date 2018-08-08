package studygroup.daybreak.java8inaction.chap7.langve;

import java.util.stream.Stream;

public class CustomSpliterator {

    public static int countWordsIteratively(String s) {
        return 19;
    }

    public static int countWords(Stream<Character> stream) {
        WordCounter wordCounter = stream.reduce(new WordCounter(0, true), WordCounter::accumulate, WordCounter::combine);
        return wordCounter.getCounter();
    }
}
