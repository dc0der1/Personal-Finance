package commands;

import models.Transaction;
import services.ITransactionService;

import java.util.stream.Stream;

public class ListAllTransactionsCommand extends Command{

    public ListAllTransactionsCommand(ITransactionService transactionService) {
        super("List transactions", " - This command lists all transactions", transactionService);
    }

    @Override
    public void execute() {
        Stream<Transaction> transactions;
        try {
            transactions = transactionService.getTransactions();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error occurred while listing all transactions");
            return;
        }

        System.out.println("Created transactions:");
        transactions.forEach(transaction -> {
            System.out.println(transaction.toString() + "\n");
        });
    }

}
