package com.jrd.itmas_client.itegration;

import com.jrd.itmas_client.infrastructure.exceptions.CommandExecutionException;
import com.jrd.itmas_client.infrastructure.exceptions.ServerCommunicationException;
import com.jrd.itmas_client.infrastructure.utils.Configuration;
import com.jrd.itmas_client.infrastructure.validation.UserDataValidator;
import com.jrd.itmas_client.interpreter.CommandExecutor;
import com.jrd.itmas_client.servercom.ServerCommunicator;
import com.jrd.itmas_client.servercom.ServerCommunicatorImpl;
import com.jrd.itmas_client.servercom.dto.UserDTO;
import com.jrd.itmas_client.ui.UIHandler;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by Kuba on 2016-07-08.
 *
 * Warning. This tests assumes that itmas_server is running!
 */
public class BackEndCommunicationTest {
    private final static String fileName = "userDataTest.txt";

    private UserDataValidator validator;

    private CommandExecutor commandExecutor;

    private ServerCommunicator serverCommunicator;

    @Before
    public void setup() throws IOException {
        this.validator = new UserDataValidator();
        this.serverCommunicator = new ServerCommunicatorImpl(new Configuration(".itmas"));
        this.commandExecutor = new CommandExecutor(serverCommunicator, new UIHandler());
    }

    @Test
    @Ignore
    public void getUserFromServerTest() throws ServerCommunicationException {
        UserDTO user = commandExecutor.showUser("user");
        Assert.assertTrue(user != null);
    }

    @Test
    @Ignore
    public void sendCreatedUserToServer() throws CommandExecutionException, ServerCommunicationException, IOException {
        UserDTO user = commandExecutor.addUser("", fileName);
        serverCommunicator.deleteUser(user.getLogin());
        Assert.assertTrue(user != null);
        Assert.assertTrue("jradlak".equals(user.getLogin()));
    }
}
