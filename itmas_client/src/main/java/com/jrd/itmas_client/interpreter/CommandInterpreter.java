package com.jrd.itmas_client.interpreter;

import com.jrd.itmas_client.infrastructure.exceptions.CommandExecutionException;
import com.jrd.itmas_client.infrastructure.exceptions.ServerCommunicationException;


/**
 * Created by jakub on 05.06.16.
 */
public class CommandInterpreter {
    private CommandExecutor commandExecutor;

    public CommandInterpreter(CommandExecutor commandExecutor) {
        this.commandExecutor = commandExecutor;
    }

    public void interpret(Command command) throws ServerCommunicationException, CommandExecutionException {
        if (command.getCommandType() == Command.CommandType.USER_SHOW) {
            String userLogin = command.getParameter(0);
            commandExecutor.showUser(userLogin);
        } else if (command.getCommandType() == Command.CommandType.USER_ADD) {
            String userLogin = command.getParameter(0);
            String fileName = command.getParameter(1);
            if (fileName.isEmpty()) {
                commandExecutor.addUser(userLogin);
            } else {
                commandExecutor.addUser(userLogin, fileName);
            }
        } else if (command.getCommandType() == Command.CommandType.TASK_ALL_SHOW) {
            String userLogin = command.getParameter(0);
            commandExecutor.showTasks(userLogin);
        }
    }
}
