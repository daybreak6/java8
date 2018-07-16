package studygroup.daybreak.java8inaction.chap5.yuna;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

import static java.util.stream.Collectors.toList;

public class ChapterFive {

    private static final Logger log = LoggerFactory.getLogger(ChapterFive.class);

    private static final Trader raoul = new Trader("Raoul", "Cambridge");
    private static final Trader mario = new Trader("Mario", "Milan");
    private static final Trader alan = new Trader("Alan", "Cambridge");
    private static final Trader brian = new Trader("Brian", "Cambridge");

    private static final List<Transaction> transactions = Arrays.asList(
            new Transaction(brian, 2011, 300),
            new Transaction(raoul, 2012, 1000),
            new Transaction(raoul, 2011, 400),
            new Transaction(mario, 2012, 710),
            new Transaction(mario, 2012, 700),
            new Transaction(alan, 2012, 950)
    );

    public static void test1() {
        List<Dish> dishes = Dish.menu.stream().filter(Dish::isVegetarian).collect(toList());
        log.debug("dishes : {}", dishes.toString());

        // skip 숫자가 length보다 커도 index exception이나 runtime
        List<Dish> dishes1 = Dish.menu.stream().filter(Dish::isVegetarian).skip(0).collect(toList());
        log.debug("dishes1 : {}", dishes1.toString());

        // limit 숫자가 length보다 커도 index에러 안나네..
        List<Dish> dishes2 = Dish.menu.stream().filter(Dish::isVegetarian).limit(10).collect(toList());
        log.debug("dishes2 : {}", dishes2.toString());
    }

    public static void test2() {

        List<String> names = Dish.menu.stream().map(Dish::getName).collect(toList());
        log.debug("names : {}", names.toString());

        List<Integer> nameLength = Dish.menu.stream().map(e -> e.getName().length()).collect(toList());
        log.debug("nameLength : {}", nameLength.toString());

        List<Integer> nameLength2 = Dish.menu.stream().map(Dish::getName).map(String::length).collect(toList());
        log.debug("nameLength2 : {}", nameLength2.toString());

    }

    public static void test3() {
        int[][] tempInt = new int[][]{{1, 2}, {2, 3}};
        int[][] tempInt2 = new int[][]{{4, 4, 3}, {3, 2, 2}, {2, 1, 0}};
//        int p = 3;
//        int q = 2;
        int p = 5;
        int q = 3;
        List<Integer> tempList = new ArrayList<>();

        for (int[] arr : tempInt2) {
            tempList.addAll(Arrays.stream(arr).boxed().collect(toList()));
        }
        log.debug("tempList : {}", tempList.toString());

        int max = Collections.max(tempList);
        List<Integer> resultList = new ArrayList();

        while (max >= Collections.min(tempList)) {
            final int tMAx = max;
            resultList.add(tempList.stream().mapToInt(e -> e > tMAx ? (e - tMAx) * q : (tMAx - e) * p).sum());
            max--;
        }

        log.debug("resultList : {}", resultList.toString());
        int result = Collections.min(resultList);
        log.debug("result : {}", result);
    }

    public static void test4() {
        List<Integer> intList = Arrays.asList(1, 2, 3, 4, 5);
        List rtn = intList.stream().map(e -> Math.pow(e, 2)).collect(toList());
        log.debug("rtn : {}", rtn.toString());
        List<Integer> rtn2 = intList.stream().map(n -> n * n).collect(toList());
        log.debug("rtn2 : {}", rtn2.toString());


        List<Integer> num1 = Arrays.asList(1, 2, 3);
        List<Integer> num2 = Arrays.asList(3, 4);

        List<int[]> result = num1.stream().flatMap(i -> num2.stream().map(e -> new int[]{i, e})).collect(toList());
/*
        result.stream().peek(e ->
            System.out.println(Arrays.toString(e))
        );*/

        List result2 = result.stream().filter(e -> (e[0] + e[1]) % 3 == 0).collect(toList());
        log.debug("result2 : {}", result2.toString());
        List result3 = num1.stream().flatMap(i -> num2.stream().filter(j -> (i + j) % 3 == 0)).collect(toList());

    }

    public static void test5() {
        int count = Dish.menu.stream().map(d -> 1).reduce(0, (a, b) -> a + b);
        log.debug("count : {}", count);

        long count2 = Dish.menu.stream().count();
        log.debug("count2 : {}", count2);
    }

    public static void test6() {

        List<Transaction> resultList1 = transactions.stream().filter(e -> e.getYear() == 2011).sorted(Comparator.comparing(Transaction::getValue)).collect(toList());
        log.debug("resultList1 : {}", resultList1.toString());

        List<String> city = transactions.stream().map(e -> e.getTrader().getCity()).distinct().collect(toList());
        log.debug("city : {}", city);

        List cambridgeMan = transactions.stream().filter(e -> e.getTrader().getCity().equalsIgnoreCase("cambridge")).map(e -> e.getTrader()).distinct().sorted(Comparator.comparing(Trader::getName)).collect(toList());
        log.debug("cambridgeMan : {}", cambridgeMan.toString());

        boolean isMilan = transactions.stream().anyMatch(e -> e.getTrader().getCity().equalsIgnoreCase("milan"));
        log.debug("isMilan : {}", isMilan);

        transactions.stream().filter(e -> e.getTrader().getCity().equalsIgnoreCase("cambridge")).map(Transaction::getValue).forEach(System.out::println);

        int maxTransactionVal = Collections.max(transactions.stream().map(e -> e.getValue()).collect(toList()));
        log.debug("maxTransactionVal : {}", maxTransactionVal);

        int minTransactionVal = Collections.min(transactions.stream().map(e -> e.getValue()).collect(toList()));
        log.debug("minTransactionVal : {}", minTransactionVal);

    }
}
