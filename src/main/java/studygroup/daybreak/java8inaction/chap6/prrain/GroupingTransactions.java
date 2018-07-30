package studygroup.daybreak.java8inaction.chap6.prrain;

import static java.util.stream.Collectors.groupingBy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;
	
/**
 * 통화별 트랜잭션 그룹화
 * @author prrain
 *
 */
public class GroupingTransactions {
	
	public static List<Transaction> transactions = Arrays.asList(new Transaction(Currency.EUR, 1500.0),
			new Transaction(Currency.USD, 2300.0), new Transaction(Currency.GBP, 9900.0),
			new Transaction(Currency.EUR, 1100.0), new Transaction(Currency.JPY, 7800.0),
			new Transaction(Currency.CHF, 6700.0), new Transaction(Currency.EUR, 5600.0),
			new Transaction(Currency.USD, 4500.0), new Transaction(Currency.CHF, 3400.0),
			new Transaction(Currency.GBP, 3200.0), new Transaction(Currency.USD, 4600.0),
			new Transaction(Currency.JPY, 5700.0), new Transaction(Currency.EUR, 6800.0));

	public static void main(String[] args) {
		//명령형 버전
		groupImperatively();
		//함수형 버전 
		groupFunctionally();

	}
	/**
	 * 통화별로 트랜잭션을 그룹화한 명령형 버전 
	 */
	private static void groupImperatively() {
		System.out.println("##### 1.명령형 ########");
		int curindx = 0;
		Map<Currency, List<Transaction>> transactionsByCurrencies = new HashMap<>();
		for (Transaction transaction : transactions) {
			Currency currency = transaction.getCurrency();
			System.out.println("currency is "+currency.toString());
			List<Transaction> transactionsForCurrency = transactionsByCurrencies.get(currency);
			if (transactionsForCurrency == null) {
				System.out.println(currency.toString()+ " is null");
				transactionsForCurrency = new ArrayList<>();
				//curindx++;
				transactionsByCurrencies.put(currency, transactionsForCurrency);
				System.out.println("transactionsByCurrencies put"+transactionsByCurrencies);
			}
			//같은 통화를 ㅏ진 트랜잭션 리스트에 현재 탐색중인 트랜잭션을 추가한다. 
			transactionsForCurrency.add(transaction);
			System.out.println("transactionsForCurrency add" +transactionsForCurrency.toString());
			System.out.println("############################");
		}
		//System.out.println("call idx : "+curindx);
		System.out.println("transactionsByCurrencies idx : "+transactionsByCurrencies.size());
		System.out.println(transactionsByCurrencies);
	}
	/**
	 *  통화별로 트랜잭션을 그룹화한 함수형 버전 
	 */
	private static void groupFunctionally() {
		System.out.println("##### 2. 함수형 ########");
		
		List<Transaction> transactionList = transactions.stream().collect(Collectors.toList());
		System.out.println("transactionList" + transactionList.toString());
		
		List<Currency> currencyList = transactions.stream().map(Transaction::getCurrency).distinct().collect(Collectors.toList());
		System.out.println("currencyList" + currencyList.toString());
		
		Map<Currency, List<Transaction>> transactionsByCurrencies = transactions.stream()
				.collect(groupingBy(Transaction::getCurrency));
		System.out.println(transactionsByCurrencies);
	}

	public static class Transaction {
		private final Currency currency;
		private final double value;

		public Transaction(Currency currency, double value) {
			this.currency = currency;
			this.value = value;
		}

		public Currency getCurrency() {
			return currency;
		}

		public double getValue() {
			return value;
		}

		@Override
		public String toString() {
			return currency + " " + value;
		}
	}

	public enum Currency {
		EUR, USD, JPY, GBP, CHF
	}
}
