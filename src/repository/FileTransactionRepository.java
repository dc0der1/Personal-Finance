package repository;

import models.Transaction;
import models.TransactionType;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class FileTransactionRepository implements ITransactionRepository{

    private static final String EXTENSION = ".txt";

    @Override
    public List<Transaction> findAll() {
        ArrayList<Transaction> transactions = new ArrayList<>();

        File files = new File("./");
        File[] transactionFiles = files.listFiles();

        if (transactionFiles == null) {
            return transactions;
        }

        for (File transactionFile : transactionFiles) {
            String name = transactionFile.getName();

            if (!name.endsWith(EXTENSION)) {
                continue;
            }

            String fileName = name.substring(0, name.length() - EXTENSION.length());

            UUID transactionId;
            try {
                transactionId = UUID.fromString(fileName);
            } catch (Exception ignored) {
                continue;
            }

            Transaction transaction = findById(transactionId);
            transactions.add(transaction);
        }

        return transactions;
    }

    @Override
    public Transaction findById(UUID transactionId) {
        String fileName = getFileName(transactionId);

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            reader.readLine();
            LocalDate date = LocalDate.parse(reader.readLine());
            String name = reader.readLine();
            int amount = Integer.parseInt(reader.readLine());
            TransactionType transactionType = TransactionType.valueOf(reader.readLine());

            return new Transaction(date, name, amount, transactionType);
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
            String transactionType = String.valueOf(transaction.getTransactionType());

            stream
                    .append(transaction.getId().toString())
                    .append("\n")
                    .append(date)
                    .append("\n")
                    .append(name)
                    .append("\n")
                    .append(amount)
                    .append("\n")
                    .append(transactionType);
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
