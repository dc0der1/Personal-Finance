package commands;

import models.Transaction;
import services.ITransactionService;
import utility.GetId;
import java.util.UUID;

public class DeleteTransactionCommand extends Command{

    public DeleteTransactionCommand(ITransactionService transactionService) {
        super("Delete", " - This command deletes a transaction", transactionService);
    }

    @Override
    public void execute() {
        UUID transactionID = GetId.queryTransactionId();

        if (transactionID == null) {
            return;
        }

        Transaction transaction;
        try {
            transaction = transactionService.deleteTransaction(transactionID);
        } catch (Exception e) {
            System.out.println("An error occurred. Couldn't delete transaction.");
            return;
        }

        if (transaction == null) {
            System.out.println("No such transaction was found.");
        } else {
            System.out.println("Deleted transaction with the name of " + transaction.getName());
        }
    }
}
