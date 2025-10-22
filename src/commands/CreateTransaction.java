package commands;

import models.Transaction;
import services.ITransactionService;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.UUID;

public class CreateTransaction extends Command {

    public CreateTransaction(ITransactionService transactionService) {
        super("Create transaction", "Create and save a new transaction", transactionService);
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Create a transaction.");


        String date;
        while (true) {
            try {
                System.out.print("Enter transaction date: ");
                date = scanner.nextLine();
                LocalDate.parse(date);
                break;
            } catch (Exception e) {
                System.out.println("Invalid date. Please follow this format 'yyyy-MM-dd'");
            }
        }

        String name;
        while (true) {
            try {
                System.out.print("Enter name: ");
                name = scanner.nextLine();
                break;
            } catch (Exception e) {
                System.out.println("Please enter a valid name!");
            }
        }

        int amount;
        while (true) {
            try {
                System.out.print("Enter transaction amount: ");
                amount = scanner.nextInt();
                scanner.nextLine();
                break;
            } catch (InputMismatchException ignored) {
                System.out.println("Please enter a number!");
                scanner.nextLine();
            }
        }

        Transaction transaction = new Transaction(date, name, amount);
        try {
            transactionService.createTransaction(transaction);
            System.out.println("Transaction " + name + " has been created.");
        } catch (Exception e) {
            System.out.println("An error occurred, message: " + e.getMessage());
        }

    }
}
