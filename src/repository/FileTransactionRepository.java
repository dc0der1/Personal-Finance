package repository;

import models.Transaction;

import java.io.*;
import java.time.LocalDate;
import java.util.UUID;

public class FileTransactionRepository implements ITransactionRepository{

    private static final String EXTENSION = ".txt";

    @Override
    public Transaction findById(UUID transaction) {
        String fileName = getFileName(transaction);

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            reader.readLine();
            LocalDate date = LocalDate.parse(reader.readLine());
            String name = reader.readLine();
            int amount = Integer.parseInt(reader.readLine());

            return new Transaction(date, name, amount);
        } catch (IOException e) {
            System.out.println("Error occurred while reading file.");
            e.printStackTrace(System.out);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(Transaction transaction) throws Exception{

        String fileName = getFileName(transaction.getId());

        try (BufferedWriter stream = new BufferedWriter(new FileWriter(fileName))) {
            String date = transaction.getDate().toString();
            String name = transaction.getName();
            String amount = String.valueOf(transaction.getAmount());

            stream
                    .append(transaction.getId().toString())
                    .append("\n")
                    .append(date)
                    .append("\n")
                    .append(name)
                    .append("\n")
                    .append(amount);
        }

    }

    @Override
    public void delete(UUID id) {
        String fileName = getFileName(id);

        File file = new File(fileName);
        boolean ignored = file.delete();

    }

    private static String getFileName(UUID transactionId) {
        return transactionId.toString() + EXTENSION;
    }
}
