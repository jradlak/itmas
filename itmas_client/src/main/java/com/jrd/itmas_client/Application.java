package com.jrd.itmas_client;

import com.jrd.itmas_client.interpreter.Command;
import com.jrd.itmas_client.interpreter.CommandExecutor;
import com.jrd.itmas_client.interpreter.CommandInterpreter;
import com.jrd.itmas_client.serververcom.ServerCommunicator;
import com.jrd.itmas_client.ui.UIHandler;
import com.jrd.itmas_client.utils.Configuration;

import java.io.IOException;

/**
 * Created by Kuba on 2016-05-29.
 */
public class Application {

    private static Configuration configuration;

    private static ServerCommunicator serverCommunicator;

    private static UIHandler uiHandler;

    private static CommandExecutor commandExecutor;

    private static CommandInterpreter commandInterpreter;

    public static void main(String[] args) {
        try {
            prepareDependencies();
            String rawCommand = args[0];
            Command command = new Command(rawCommand);
            commandInterpreter.interpret(command);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void prepareDependencies() throws IOException {
        configuration = new Configuration(".itmas");
        serverCommunicator = new ServerCommunicator(configuration);
        uiHandler = new UIHandler();
        commandExecutor = new CommandExecutor(serverCommunicator, uiHandler);
        commandInterpreter = new CommandInterpreter(commandExecutor);
    }
}
