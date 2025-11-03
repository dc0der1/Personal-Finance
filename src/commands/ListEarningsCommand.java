package commands;

import models.Transaction;
import models.TransactionType;
import services.ITransactionService;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.time.YearMonth;
import java.time.temporal.WeekFields;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ListEarningsCommand extends Command{

    public ListEarningsCommand(ITransactionService transactionService) {
        super("Earnings", " - This command lists transaction earnings", transactionService);
    }

    // 1. Get the dates of the transaction
    // 2. Get the earnings only
    // 3. If there are multiple earnings in the same day we add the sum

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter to check daily, weekly, monthly or yearly earnings: ");
        String input = scanner.nextLine();

        if (input.equalsIgnoreCase("daily")) {
            displayDailyEarnings();
        } else if (input.equalsIgnoreCase("weekly")) {
            displayWeeklyEarnings();
        } else if (input.equalsIgnoreCase("monthly")) {
            displayMonthlyEarnings();
        } else if (input.equalsIgnoreCase("yearly")) {
            displayYearlyEarnings();
        }
    }

    public void displayDailyEarnings() {
        try {
            Stream<Transaction> transactions = transactionService.getTransactions();
            // Filters out expense types
            // Groups the dates and sums up the amount of that date
            Map<LocalDate, Integer> dailyEarnings = transactions
                    // Filters out expense types
                    .filter(transaction -> transaction.getTransactionType() == TransactionType.EARNING)
                    // Groups the dates and sums up the amount of that date
                    .collect(Collectors.groupingBy(
                        Transaction::getDate, Collectors.summingInt(Transaction::getAmount)
                    ));

            dailyEarnings.forEach((date, total) ->
                    System.out.println("Date: " + date + ", Total earnings this day: " + total)
            );
        } catch (Exception e) {
            System.out.println("Error occurred while listing daily earnings.");
        }
    }

    public void displayWeeklyEarnings() {
        WeekFields weekFields = WeekFields.of(Locale.getDefault());

        try {
            Stream<Transaction> transactions = transactionService.getTransactions();

            Map<Integer, Integer> weeklyEarnings = transactions
                    .filter(transaction -> transaction.getTransactionType() == TransactionType.EARNING)
                    .collect(Collectors.groupingBy(
                        transaction -> transaction.getDate().get(weekFields.weekOfWeekBasedYear()), Collectors.summingInt(Transaction::getAmount)
                    ));
            weeklyEarnings.forEach((weekNumber, total) ->
                    System.out.println("Week: " + weekNumber + ", Total earnings this week: " + total)
            );
        } catch (Exception e) {
            System.out.println("Error occurred while listing weekly earnings.");
        }
    }

    public void displayMonthlyEarnings() {

        try {
            Stream<Transaction> transactions = transactionService.getTransactions();

            Map<Month, Integer> weeklyEarnings = transactions
                    .filter(transaction -> transaction.getTransactionType() == TransactionType.EARNING)
                    .collect(Collectors.groupingBy(
                            transaction -> transaction.getDate().getMonth(), Collectors.summingInt(Transaction::getAmount)
                    ));
            weeklyEarnings.forEach((month, total) ->
                    System.out.println("Week: " + month + ", Total earnings this month: " + total)
            );
        } catch (Exception e) {
            System.out.println("Error occurred while listing monthly earnings.");
        }
    }

    public void displayYearlyEarnings() {

        try {
            Stream<Transaction> transactions = transactionService.getTransactions();

            Map<Integer, Integer> weeklyEarnings = transactions
                    .filter(transaction -> transaction.getTransactionType() == TransactionType.EARNING)
                    .collect(Collectors.groupingBy(
                            transaction -> transaction.getDate().getYear(), Collectors.summingInt(Transaction::getAmount)
                    ));
            weeklyEarnings.forEach((year, total) ->
                    System.out.println("Week: " + year + ", Total earnings this year: " + total)
            );
        } catch (Exception e) {
            System.out.println("Error occurred while listing yearly earnings.");
        }
    }
}
