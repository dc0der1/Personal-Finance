package repository;

import models.Transaction;

import java.io.File;
import java.io.FileWriter;
import java.time.LocalDate;

public class FileTransactionRepository implements ITransactionRepository{

    private final String EXTENSION = ".txt";

    @Override
    public void save(Transaction transaction) {

        String fileName = transaction.getId().toString();

        File file = new File(fileName);

        try (FileWriter writer = new FileWriter(file)) {
            String date = String.valueOf(transaction.getDate());
            writer.write(date + "\n" + transaction.getName() + "\n" + transaction.getAmount());

        } catch (Exception e) {
            System.out.println("An error occurred");
        }

    }

    @Override
    public void delete() {

    }

    @Override
    public void update() {

    }

    @Override
    public void read() {

    }
}
