package services;

import models.Transaction;
import repository.ITransactionRepository;

import java.util.UUID;
import java.util.stream.Stream;

public class DefaultTransactionService implements ITransactionService{

    private final ITransactionRepository fileTransactionRepository;

    public DefaultTransactionService(ITransactionRepository fileTransactionRepository) {
        this.fileTransactionRepository = fileTransactionRepository;
    }

    // CREATE transaction
    @Override
    public void createTransaction(Transaction transaction) throws Exception{
        fileTransactionRepository.save(transaction);
    }

    // DELETE
    @Override
    public Transaction deleteTransaction(UUID id) {
        Transaction transaction = fileTransactionRepository.findById(id);
        fileTransactionRepository.delete(id);
        return transaction;
    }

    // Find by ID
    @Override
    public void findTransactionById(Transaction transaction) {

    }

    @Override
    public Stream<Transaction> searchTransactions(String id) {
        return getTransactions()
                .filter((transaction) -> transaction.getName().toLowerCase().contains(id.toLowerCase()))
                .sorted((a, b) -> a.getName().compareToIgnoreCase(b.getName()));
    }

    @Override
    public Stream<Transaction> getTransactions() {
        return fileTransactionRepository.findAll().stream();
    }

}
