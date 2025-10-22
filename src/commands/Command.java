package commands;

// Command super class that does different things create, read, update, delete, etc.

public abstract class Command {

    protected String name;
    protected String description;

    public Command(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public abstract void execute();

    public String getName() { return name; }
    public String getDescription() { return description; }
}
