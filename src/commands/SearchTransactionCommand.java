package commands;

import models.Transaction;
import services.ITransactionService;

import java.util.Scanner;
import java.util.stream.Stream;

public class SearchTransactionCommand extends Command{

    public SearchTransactionCommand(ITransactionService transactionService) {
        super("Search transaction", " - This command finds transaction based on your search", transactionService);
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter transaction name: ");
        String query = scanner.nextLine();

        Stream<Transaction> stream;
        try {
            stream = transactionService.searchTransactions(query);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error while searching for transactions!");
            return;
        }

        stream.forEach(transaction -> {
            System.out.println(transaction.toString());
        });
    }

}
