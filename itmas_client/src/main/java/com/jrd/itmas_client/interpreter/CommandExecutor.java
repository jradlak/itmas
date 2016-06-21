package com.jrd.itmas_client.interpreter;

import com.jrd.itmas_client.infrastructure.exceptions.ServerCommunicationException;
import com.jrd.itmas_client.servercom.ServerCommunicator;
import com.jrd.itmas_client.servercom.dto.UserDTO;
import com.jrd.itmas_client.ui.UIHandler;

import java.io.IOException;

/**
 * Created by jakub on 05.06.16.
 */
public class CommandExecutor {

    private ServerCommunicator serverCommunicator;

    private UIHandler uiHandler;

    public CommandExecutor(ServerCommunicator serverCommunicator, UIHandler uiHandler) {
        this.serverCommunicator = serverCommunicator;
        this.uiHandler = uiHandler;
    }

    public void showUser(String userLogin) throws ServerCommunicationException {
        UserDTO userDTO = serverCommunicator.getUserDataFromServer(userLogin);
        uiHandler.printUserData(userDTO);
    }

    public void addUser(String userLogin) throws ServerCommunicationException {
        UserDTO userDTO = getNewUserInfo(userLogin);
        serverCommunicator.userAdd(userDTO);
    }

    private UserDTO getNewUserInfo(String userLogin) {
        UserDTO user = new UserDTO();
        //TODO!!!
        return user;
    }
}
