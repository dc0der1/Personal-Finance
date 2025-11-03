package commands;

import services.ITransactionService;

public class ListEarningsCommand extends Command{

    public ListEarningsCommand(ITransactionService transactionService) {
        super("Earnings", " - This command lists transaction earnings", transactionService);
    }

    @Override
    public void execute() {

    }

}
