package services;

import commands.Command;

import java.util.ArrayList;
import java.util.List;

public interface ICommandService {

    void registerCommand(Command command);
    void executeCommand(String commandInput);

}
