package com.jrd.itmas_client.interpreter;

import java.io.IOException;

/**
 * Created by jakub on 05.06.16.
 */
public class CommandInterpreter {
    private CommandExecutor commandExecutor;

    public CommandInterpreter(CommandExecutor commandExecutor) {
        this.commandExecutor = commandExecutor;
    }

    public void interpret(Command command) throws IOException {
        if (command.getCommandType() == Command.CommandType.USER_SHOW) {
            String userLogin = command.getParameters()[0];
            commandExecutor.showUser(userLogin);
        }
    }
}
