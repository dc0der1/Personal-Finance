package services;

import models.Transaction;

public interface ITransactionService {

    void createTransaction(Transaction transaction);
    void deleteTransaction();
    void readTransaction();
    void updateTransaction();
    void findTransactionById(Transaction transaction);

}
