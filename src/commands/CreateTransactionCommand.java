package commands;

import models.Transaction;
import services.ITransactionService;

import java.time.LocalDate;
import java.util.Scanner;

// We get name and description from super class
public class CreateTransactionCommand extends Command{

    public CreateTransactionCommand(String name, String description, ITransactionService transactionService) {
        super("Create transaction - ", "This command creates transaction", transactionService);
    }

    // Here we create class, we use the same method from super class but override it
    @Override
    public void execute() {
        // Start with input
        Scanner scanner = new Scanner(System.in);

        LocalDate date;
        while (true) {
            try {
                System.out.print("Enter date: ");
                String dateInput = scanner.nextLine();
                date = LocalDate.parse(dateInput);
                break;
            } catch (Exception ignored) {
                System.out.println("Enter valid date! Follow this format (yyyy-MM-dd)");
            }
        }

        // Don't need to validate because the program won't crash if user enters number
        System.out.print("Enter name: ");
        String name = scanner.nextLine();

        int amount;
        while (true) {
            try {
                System.out.print("Enter amount: ");
                amount = scanner.nextInt();
                break;
            } catch (Exception ignored) {
                System.out.println("Please enter a valid number!");
            }
        }

        // Create new transaction
        Transaction transaction = new Transaction(date, name, amount);
        transactionService.createTransaction(transaction);
    }
}
