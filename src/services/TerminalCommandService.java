package services;

import commands.Command;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TerminalCommandService implements ICommandService{
    private final List<Command> commands = new ArrayList<>();

    public void start() {
        System.out.println("Finance app application");
        System.out.println("Choose from the following commands:");

        for (Command command : commands) {
            System.out.println(command);
        }

        System.out.println("exit - Exit the application");

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
                System.out.println("Invalid command");
            }
        }
    }

    @Override
    public void registerCommand(Command command) {
        this.commands.add(command);
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
}
