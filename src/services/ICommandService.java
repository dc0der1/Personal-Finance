package services;

import commands.Command;

public interface ICommandService {

    void registerCommand(Command command);
    void executeCommand(String commandInput);

}
