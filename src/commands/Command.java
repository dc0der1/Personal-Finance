package commands;

import services.ITransactionService;

public abstract class Command {

    // Attributes
    protected final String name;
    protected final String description;
    protected final ITransactionService transactionService;

    // Constructor
    public Command(String name, String description, ITransactionService transactionService) {
        this.name = name;
        this.description = description;
        this.transactionService = transactionService;
    }

    public abstract void execute();

    // Getters
    public String getName() { return name; }

    public String getDescription() { return description; }

    @Override
    public String toString() {
        return name + " - " + description;
    }
}
