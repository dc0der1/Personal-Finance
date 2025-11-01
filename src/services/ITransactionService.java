package services;

import models.Transaction;

import java.util.UUID;
import java.util.stream.Stream;

public interface ITransactionService {

    void createTransaction(Transaction transaction) throws Exception;
    Transaction deleteTransaction(UUID id);
    void findTransactionById(Transaction transaction);
    Stream<Transaction> searchTransactions(String id);
    Stream<Transaction> getTransactions();

}
