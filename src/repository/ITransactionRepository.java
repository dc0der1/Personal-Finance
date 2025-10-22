package repository;

import models.Transaction;

public interface ITransactionRepository {
    void save(Transaction transaction);
}
