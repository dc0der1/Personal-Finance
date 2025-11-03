
import commands.CreateTransactionCommand;
import commands.DeleteTransactionCommand;
import commands.ListAllTransactionsCommand;
import commands.SearchTransactionCommand;
import repository.FileTransactionRepository;
import services.DefaultTransactionService;
import services.ICommandService;
import services.ITransactionService;
import services.TerminalCommandService;

public class Main {
    public static void main(String[] args) {

        ICommandService commandService = new TerminalCommandService();
        ITransactionService transactionService = new DefaultTransactionService(new FileTransactionRepository());

        commandService.registerCommand(new CreateTransactionCommand(transactionService));
        commandService.registerCommand(new DeleteTransactionCommand(transactionService));
        commandService.registerCommand(new SearchTransactionCommand(transactionService));
        commandService.registerCommand(new ListAllTransactionsCommand(transactionService));

        if (commandService instanceof TerminalCommandService service) {
            service.start();
        }

    }
}
