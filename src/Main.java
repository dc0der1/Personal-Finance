import commands.CreateTransaction;
import repository.FileRepository;
import services.DefaultTransactionService;
import services.ICommandService;
import services.ITransactionService;
import services.TerminalCommandService;

// Inspired from todo project

public class Main {
    public static void main(String[] args) {

        TerminalCommandService commandService = new TerminalCommandService();
        ITransactionService transactionService = new DefaultTransactionService(new FileRepository());

        commandService.registerCommand(new CreateTransaction(transactionService));

        commandService.start();

    }
}
