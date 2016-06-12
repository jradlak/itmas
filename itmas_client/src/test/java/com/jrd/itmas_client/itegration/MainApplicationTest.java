package com.jrd.itmas_client.itegration;

import com.jrd.itmas_client.interpreter.Command;
import com.jrd.itmas_client.interpreter.CommandExecutor;
import com.jrd.itmas_client.interpreter.CommandInterpreter;
import com.jrd.itmas_client.serververcom.ServerCommunicator;
import com.jrd.itmas_client.ui.UIHandler;
import com.jrd.itmas_client.utils.Configuration;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by jakub on 08.06.16.
 */
public class MainApplicationTest {
    private static Configuration configuration;

    private static ServerCommunicator serverCommunicator;

    private static UIHandler uiHandler;

    private static CommandExecutor commandExecutor;

    private static CommandInterpreter commandInterpreter;

    @Before
    public void setup() throws IOException {
        prepareDependencies();
    }

    @Test
    public void testRetreiveUserInfoFromServer() throws IOException {
        interpretCommand("user show");
    }

    @Test
    public void testGetExplicitUserInfoFromServer() throws IOException {
        interpretCommand("user 'user' show");
    }

    private void interpretCommand(String rawCommand) throws IOException {
        Command command = new Command(rawCommand);
        commandInterpreter.interpret(command);
    }

    private static void prepareDependencies() throws IOException {
        configuration = new Configuration(".itmas");
        serverCommunicator = new ServerCommunicator(configuration);
        uiHandler = new UIHandler();
        commandExecutor = new CommandExecutor(serverCommunicator, uiHandler);
        commandInterpreter = new CommandInterpreter(commandExecutor);
    }
}
