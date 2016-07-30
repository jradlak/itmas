package com.jrd.itmas_client.itegration;

import com.jrd.itmas_client.infrastructure.exceptions.CommandExecutionException;
import com.jrd.itmas_client.infrastructure.exceptions.ServerCommunicationException;
import com.jrd.itmas_client.infrastructure.utils.UserDataFileReader;
import com.jrd.itmas_client.infrastructure.validation.UserDataValidator;
import com.jrd.itmas_client.infrastructure.validation.ValidationResult;
import com.jrd.itmas_client.interpreter.CommandExecutor;
import com.jrd.itmas_client.servercom.ServerCommunicator;
import com.jrd.itmas_client.servercom.dto.UserDTO;
import com.jrd.itmas_client.ui.UIHandler;
import com.jrd.itmas_server.api.rest.dto.TaskDTO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

/**
 * Created by Kuba on 2016-07-08.
 */
public class TestReadUserData {

    private final static String fileName = "userDataTest.txt";

    private UserDataValidator validator;

    private CommandExecutor commandExecutor;

    @Before
    public void setup() {
        this.validator = new UserDataValidator();
        ServerCommunicator serverCommunicator = new ServerCommunicator() {
            @Override
            public UserDTO getUserDataFromServer(String userLogin) throws ServerCommunicationException {
                return new UserDTO();
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

        this.commandExecutor = new CommandExecutor(serverCommunicator, new UIHandler());
    }

    @Test
    public void validateUserDtoTest() throws IOException {
        List<String[]> userData = UserDataFileReader.readUserData(fileName);
        boolean result = userData.stream().map(l -> validator.validateUserDataLine(l)).allMatch(ValidationResult::isCorrent);
        Assert.assertTrue(result == true);
    }

    @Test
    public void createUserTest() throws IOException, CommandExecutionException, ServerCommunicationException {
        UserDTO userAdded = commandExecutor.addUser("", fileName);

        Assert.assertTrue("Jakub".equals(userAdded.getFirstName()));
        Assert.assertTrue("Radlak".equals(userAdded.getLastName()));
        Assert.assertTrue("jradlak".equals(userAdded.getLogin()));
        Assert.assertTrue("T!est123#@OK".equals(userAdded.getPassword()));
        Assert.assertTrue("mtestowy@test.com".equals(userAdded.getEmail()));
        Assert.assertTrue("ROLE USER".equals(userAdded.getAuthorities().stream().findFirst().get()));
    }
}
