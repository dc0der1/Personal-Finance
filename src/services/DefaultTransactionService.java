package services;

import models.Transaction;
import repository.FileTransactionRepository;

public class DefaultTransactionService implements ITransactionService{

    private FileTransactionRepository fileTransactionRepository;

    // CREATE transaction
    @Override
    public void createTransaction(Transaction transaction) {
        fileTransactionRepository.save(transaction);
    }

    // DELETE
    @Override
    public void deleteTransaction() {

    }

    // Read / GET
    @Override
    public void readTransaction() {

    }

    // Update / PUT
    @Override
    public void updateTransaction() {

    }

    // Find by ID
    @Override
    public void findTransactionById(Transaction transaction) {

    }

}
