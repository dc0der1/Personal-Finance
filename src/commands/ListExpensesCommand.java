package commands;

import models.Transaction;
import models.TransactionType;
import services.ITransactionService;

import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.WeekFields;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ListExpensesCommand extends Command {

    public ListExpensesCommand(ITransactionService transactionService) {
        super("Expenses", " - This command lists all expenses", transactionService);
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter to check daily, weekly, monthly or yearly earnings: ");
        String input = scanner.nextLine();

        if (input.equalsIgnoreCase("daily")) {
            displayDailyExpenses();
        } else if (input.equalsIgnoreCase("weekly")) {
            displayWeeklyExpenses();
        } else if (input.equalsIgnoreCase("monthly")) {
            displayMonthlyExpenses();
        } else if (input.equalsIgnoreCase("yearly")) {
            displayYearlyExpenses();
        }
    }

    public void displayDailyExpenses() {
        try {
            Stream<Transaction> transactions = transactionService.getTransactions();
            // Filters out expense types
            // Groups the dates and sums up the amount of that date
            Map<LocalDate, Integer> dailyExpenses = transactions
                    // Filters out expense types
                    .filter(transaction -> transaction.getTransactionType() == TransactionType.EXPENSE)
                    // Groups the dates and sums up the amount of that date
                    .collect(Collectors.groupingBy(
                            Transaction::getDate, Collectors.summingInt(Transaction::getAmount)
                    ));

            dailyExpenses.forEach((date, total) ->
                    System.out.println("Date: " + date + ", Total earnings this day: " + total)
            );
        } catch (Exception e) {
            System.out.println("Error occurred while listing daily earnings.");
        }
    }

    public void displayWeeklyExpenses() {
        WeekFields weekFields = WeekFields.of(Locale.getDefault());

        try {
            Stream<Transaction> transactions = transactionService.getTransactions();

            Map<Integer, Integer> weeklyExpenses = transactions
                    .filter(transaction -> transaction.getTransactionType() == TransactionType.EXPENSE)
                    .collect(Collectors.groupingBy(
                            transaction -> transaction.getDate().get(weekFields.weekOfWeekBasedYear()), Collectors.summingInt(Transaction::getAmount)
                    ));
            weeklyExpenses.forEach((weekNumber, total) ->
                    System.out.println("Week: " + weekNumber + ", Total earnings this week: " + total)
            );
        } catch (Exception e) {
            System.out.println("Error occurred while listing weekly earnings.");
        }
    }

    public void displayMonthlyExpenses() {

        try {
            Stream<Transaction> transactions = transactionService.getTransactions();

            Map<Month, Integer> monthlyExpenses = transactions
                    .filter(transaction -> transaction.getTransactionType() == TransactionType.EXPENSE)
                    .collect(Collectors.groupingBy(
                            transaction -> transaction.getDate().getMonth(), Collectors.summingInt(Transaction::getAmount)
                    ));
            monthlyExpenses.forEach((month, total) ->
                    System.out.println("Week: " + month + ", Total earnings this month: " + total)
            );
        } catch (Exception e) {
            System.out.println("Error occurred while listing monthly earnings.");
        }
    }

    public void displayYearlyExpenses() {
        try {
            Stream<Transaction> transactions = transactionService.getTransactions();

            Map<Integer, Integer> yearlyExpenses = transactions
                    .filter(transaction -> transaction.getTransactionType() == TransactionType.EXPENSE)
                    .collect(Collectors.groupingBy(
                            transaction -> transaction.getDate().getYear(), Collectors.summingInt(Transaction::getAmount)
                    ));
            yearlyExpenses.forEach((year, total) ->
                    System.out.println("Week: " + year + ", Total earnings this year: " + total)
            );
        } catch (Exception e) {
            System.out.println("Error occurred while listing yearly earnings.");
        }
    }

}
