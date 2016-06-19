package com.jrd.itmas_client.interpreter;

import com.jrd.itmas_client.infrastructure.exceptions.ServerCommunicationException;

import java.io.IOException;
import java.util.ServiceConfigurationError;

/**
 * Created by jakub on 05.06.16.
 */
public class CommandInterpreter {
    private CommandExecutor commandExecutor;

    public CommandInterpreter(CommandExecutor commandExecutor) {
        this.commandExecutor = commandExecutor;
    }

    public void interpret(Command command) throws ServerCommunicationException {
        if (command.getCommandType() == Command.CommandType.USER_SHOW) {
            String userLogin = command.getParameter(0);
            System.out.println("Command interpret, user login =" + userLogin);
            commandExecutor.showUser(userLogin);
        }
    }
}
