package com.jrd.itmas_client.interpreter;

import com.jrd.itmas_client.infrastructure.exceptions.ServerCommunicationException;
import com.jrd.itmas_client.servercom.dto.UserDTO;

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
            commandExecutor.showUser(userLogin);
        } else if (command.getCommandType() == Command.CommandType.USER_ADD) {
            String userLogin = command.getParameter(0);
            commandExecutor.addUser(userLogin);
        }
    }

}
