package repository;

import models.Transaction;
import services.ITransactionService;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileRepository implements ITransactionRepository {
    private static final String EXTENSION = ".txt";
    private static ArrayList<File> transactions = new ArrayList<>();


    public static ArrayList<File> getTransactions() {
        return transactions;
    }

    public void setTransactions(ArrayList<File> transactions) {
        FileRepository.transactions = transactions;
    }

    @Override
    public void save(Transaction transaction) {
        String filename = transaction.getId();
        File file = new File(filename);
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(transaction.getDate() + "\n" + transaction.getName() + "\n" + transaction.getAmount() + "\n" + transaction.getId());

        } catch (Exception e) {
            System.out.println("An error occurred.");
        }
    }

//    @Override
//    public void delete(String id) {
//        //String filePath = "C:\\Users\\WassupBaby\\Desktop\\Java Finance app OOP design\\Personal Finance App\\" + id;
//        File file = new File("C:\\Users\\WassupBaby\\Desktop\\Java Finance app OOP design\\Personal Finance App\\" + id + EXTENSION);
//        System.out.println(file);
//
//        // To be continued THIS WORKS
//        if (file.exists()) {
//            System.out.println("File exists");
//            if (file.delete()) {
//                System.out.println("File deleted successfully");
//            }
//        } else {
//            System.out.println("File doesn't exist");
//        }
//    }
//
//    public void displayTransactions() {
//        for (File transaction : transactions) {
//            System.out.println(transaction.getName());
//        }
//    }
}
