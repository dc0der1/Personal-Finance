package services;

import models.Transaction;

import java.util.UUID;

public interface ITransactionService {

    void createTransaction(Transaction transaction) throws Exception;
    Transaction deleteTransaction(UUID id);
    void findTransactionById(Transaction transaction);

}
