package commands;

import models.Transaction;
import services.ITransactionService;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class GetBalance extends Command{

    public GetBalance(ITransactionService transactionService) {
        super("Balance", " - This command shows your balance based on transactions", transactionService);
    }

    @Override
    public void execute() {
        Stream<Transaction> transactions = transactionService.getTransactions();
        AtomicInteger sum = new AtomicInteger();

        transactions.forEach(transaction -> {
            sum.addAndGet(transaction.getAmount());
        });

        System.out.println("Balance: " + sum + "$");
    }

}
