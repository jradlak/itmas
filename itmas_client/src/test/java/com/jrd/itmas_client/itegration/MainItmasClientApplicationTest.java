package com.jrd.itmas_client.itegration;

import com.jrd.itmas_client.infrastructure.exceptions.CommandExecutionException;
import com.jrd.itmas_client.infrastructure.exceptions.ServerCommunicationException;
import com.jrd.itmas_client.interpreter.Command;
import com.jrd.itmas_client.interpreter.CommandExecutor;
import com.jrd.itmas_client.interpreter.CommandInterpreter;
import com.jrd.itmas_client.servercom.ServerCommunicator;
import com.jrd.itmas_client.servercom.dto.UserDTO;
import com.jrd.itmas_client.ui.UIHandler;
import com.jrd.itmas_client.infrastructure.utils.*;
import com.jrd.itmas_server.api.rest.dto.TaskDTO;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * Created by jakub on 08.06.16.
 */
public class MainItmasClientApplicationTest {

    private static Configuration configuration;

    private static ServerCommunicator serverCommunicator;

    private static UIHandler uiHandler;

    private static CommandExecutor commandExecutor;

    private static CommandInterpreter commandInterpreter;

    private final static UserDTO adminDTO = new UserDTO(
            "admin", "adminPass", "adminFirstName", "adminLastName", "adminEmail", new LinkedHashSet<>(), true
    );

    private final static UserDTO userDTO = new UserDTO(
            "user", "userPass", "userFirstName", "userLastName", "userEmail", new LinkedHashSet<>(), true
    );

    @Before
    public void setup() throws IOException {
        prepareDependencies();
    }

    @Test
    public void testRetreiveUserInfoFromServer() throws ServerCommunicationException, CommandExecutionException {
        interpretCommand("user show");
    }

    @Test
    public void testGetExplicitUserInfoFromServer() throws ServerCommunicationException, CommandExecutionException {
        interpretCommand("user 'user' show");
    }

    private void interpretCommand(String rawCommand) throws ServerCommunicationException, CommandExecutionException {
        Command command = new Command(rawCommand);
        commandInterpreter.interpret(command);
    }

    private static void prepareDependencies() throws IOException {
        Literals.prepareYaml("PL");
        configuration = new Configuration(".itmas");
        serverCommunicator = new ServerCommunicator() {
            @Override
            public UserDTO getUserDataFromServer(String userLogin) throws ServerCommunicationException {
                if (userLogin != null && !userLogin.isEmpty()) {
                    return userDTO;
                }

                return adminDTO;
            }

            @Override
            public UserDTO userAdd(UserDTO userDTO) throws ServerCommunicationException {
                return userDTO;
            }

            @Override
            public void deleteUser(String userLogin) throws ServerCommunicationException {
            }

            @Override
            public List<TaskDTO> allUserTasks(String userLogin) throws ServerCommunicationException {
                return null;
            }
        };

        uiHandler = new UIHandler();
        commandExecutor = new CommandExecutor(serverCommunicator, uiHandler);
        commandInterpreter = new CommandInterpreter(commandExecutor);
    }
}
