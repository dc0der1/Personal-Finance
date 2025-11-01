package repository;

import models.Transaction;

import java.util.List;
import java.util.UUID;

public interface ITransactionRepository {
    void save(Transaction transaction) throws Exception;
    void delete(UUID id);
    Transaction findById(UUID id);
    List<Transaction> findAll();
}
