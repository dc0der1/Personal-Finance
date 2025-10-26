package services;

import commands.Command;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TerminalCommandService implements ICommandService{
    private final List<Command> commands = new ArrayList<>();

    @Override
    public void registerCommand(Command command) {
        this.commands.add(command);
        System.out.println("--- Registered the command ---" + command.getName());
    }

    @Override
    public void executeCommand(String commandInput) {
        for (Command command : commands) {
            if (command.getName().equalsIgnoreCase(commandInput)) {
                command.execute();
                return;
            }
        }
    }

    public void start() {
        System.out.println("--- Personal Finance App ---");
        System.out.println("Choose from the following commands. Do /help when you forget the commands");
        System.out.println("Commands | Description");

        for (Command command : commands) {
            System.out.println(command.getName() + command.getDescription());
        }

        System.out.println("Exit - Exits the program");

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Enter command: ");
            String commandInput = scanner.nextLine();
            if (commandInput.equalsIgnoreCase("exit")) {
                return;
            }

            try {
                executeCommand(commandInput);
            } catch (Exception ignored) {
                System.out.println("An error occurred");
            }
        }
    }
}
