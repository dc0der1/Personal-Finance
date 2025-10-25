package repository;

import models.Transaction;

public interface ITransactionRepository {
    void save(Transaction transaction);
    void delete();
    void update();
    void read();
}
