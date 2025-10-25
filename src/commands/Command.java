package commands;

// Command super class that does different things create, read, update, delete, etc.

import services.ITransactionService;

public abstract class Command {

    protected String name;
    protected String description;
    protected ITransactionService transactionService;

    public Command(String name, String description, ITransactionService transactionService) {
        this.name = name;
        this.description = description;
        this.transactionService = transactionService;
    }

    public abstract void execute();

    public String getName() { return name; }
    public String getDescription() { return description; }
}
