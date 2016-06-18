package com.jrd.itmas_client;

import com.jrd.itmas_client.infrastructure.exceptions.CommandSyntaxException;
import com.jrd.itmas_client.interpreter.Command;
import com.jrd.itmas_client.interpreter.CommandExecutor;
import com.jrd.itmas_client.interpreter.CommandInterpreter;
import com.jrd.itmas_client.servercom.ServerCommunicator;
import com.jrd.itmas_client.servercom.ServerCommunicatorImpl;
import com.jrd.itmas_client.ui.UIHandler;
import com.jrd.itmas_client.infrastructure.utils.*;

import java.io.IOException;

/**
 * Created by Kuba on 2016-05-29.
 */
public class ItmasClientApplication {

    private static Configuration configuration;

    private static ServerCommunicator serverCommunicator;

    private static UIHandler uiHandler;

    private static CommandExecutor commandExecutor;

    private static CommandInterpreter commandInterpreter;

    public static void main(String[] args) {
        try {
            prepareDependencies();
            checkCommanLine(args);
            String rawCommand = args[0];
            Command command = new Command(rawCommand);
            commandInterpreter.interpret(command);
        } catch (Exception e) {
            uiHandler.printErrorMessage(e);
        }
    }

    private static void prepareDependencies() throws IOException {
        configuration = new Configuration(".itmas");
        serverCommunicator = new ServerCommunicatorImpl(configuration);
        uiHandler = new UIHandler();
        commandExecutor = new CommandExecutor(serverCommunicator, uiHandler);
        commandInterpreter = new CommandInterpreter(commandExecutor);
    }

    private static void checkCommanLine(String[] args) throws Exception {
        if (args.length == 0) {
            throw new CommandSyntaxException("There is no arguments. To get help please tape: itmas -help");
        }
    }
}
