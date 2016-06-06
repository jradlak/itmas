package com.jrd.itmas_client.interpreter;

import com.jrd.itmas_client.serververcom.ServerCommunicator;
import com.jrd.itmas_client.serververcom.dto.UserDTO;
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

    public void showUser(String userLogin) throws IOException {
        UserDTO userDTO = serverCommunicator.getUserDataFromServer(userLogin);
        uiHandler.printUserData(userDTO);
    }
}
