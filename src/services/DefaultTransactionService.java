package services;

import models.Transaction;
import repository.ITransactionRepository;

public class DefaultTransactionService implements ITransactionService{

    private final ITransactionRepository transactionRepository;

    public DefaultTransactionService(ITransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public void createTransaction(Transaction transaction) {
        transactionRepository.save(transaction);
    }
}
