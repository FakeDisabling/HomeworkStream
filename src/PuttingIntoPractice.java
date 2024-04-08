import com.sun.jdi.Value;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class PuttingIntoPractice {

    public static void main(String... args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

        // 1. Найти все транзакции за 2011 год и отсортировать их по сумме (от меньшей
        //к большей).

        System.out.printf("Все транкз");
        transactions.stream()
                .filter(x -> x.getYear() == 2011)
                .sorted(Comparator.comparing(Transaction::getValue))
                .forEach(System.out::println);

        // 2. Вывести список неповторяющихся городов, в которых работают трейдеры.
        System.out.printf("Список неповторяющихся городов\n");
        transactions.stream()
                .map(Transaction::getTrader)
                .map(Trader::getCity)
                .distinct()
                .forEach(System.out::println);

        // 3. Найти всех трейдеров из Кембриджа и отсортировать их по именам.

        System.out.println("Все трейдеры из Кембирджа");
        transactions.stream()
                .map(Transaction::getTrader)
                .filter(x -> x.getCity() == "Cambridge")
                .map(Trader::getName)
                .distinct()
                .forEach(System.out::println);

        // 4. Вернуть строку со всеми именами трейдеров, отсортированными в алфавитном
        //порядке.

        System.out.println("Все имена трейдеров");
        transactions.stream()
                .map(Transaction::getTrader)
                .map(Trader::getName)
                .sorted()
                .distinct()
                .forEach(System.out::println);

        // 5. Выяснить, существует ли хоть один трейдер из Милана.

        Boolean traderForMilan = transactions.stream()
                .map(Transaction::getTrader)
                .anyMatch(x -> x.getCity() == "Milan");

        System.out.printf("Есть ли трейдер в милане? " + traderForMilan);

        // 6. Вывести суммы всех транзакций трейдеров из Кембриджа.

        int sum = transactions.stream()
                .filter(x -> x.getTrader().getCity().equals("Cambridge"))
                .map(Transaction::getValue)
                .reduce(0, (a, b) -> a + b);

        System.out.printf("\nСумма всех транкзаций трейдеров из Кембриджа: " + sum);

        // 7. Какова максимальная сумма среди всех транзакций?

        int max = transactions.stream()
                .map(Transaction::getValue)
                .max(Integer::compareTo).get();

        System.out.println("\nМаксимальная сумма среди всех транкзаций: " + max);

        // 8. Найти транзакцию с минимальной суммой.

        int min = transactions.stream()
                .map(Transaction::getValue)
                .min(Integer::compareTo).get();

        System.out.println("\nМинимальная сумма среди всех транкзаций: " + min);
    }
}