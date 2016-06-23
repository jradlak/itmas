package com.jrd.itmas_client.interpreter;

import com.jrd.itmas_client.infrastructure.exceptions.CommandExecutionException;
import com.jrd.itmas_client.infrastructure.exceptions.ServerCommunicationException;
import com.jrd.itmas_client.infrastructure.utils.FileReader;
import com.jrd.itmas_client.infrastructure.validation.UserDataValidator;
import com.jrd.itmas_client.infrastructure.validation.ValidationResult;
import com.jrd.itmas_client.servercom.ServerCommunicator;
import com.jrd.itmas_client.servercom.dto.UserDTO;
import com.jrd.itmas_client.ui.UIHandler;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by jakub on 05.06.16.
 */
public class CommandExecutor {

    private ServerCommunicator serverCommunicator;

    private UIHandler uiHandler;

    private UserDataValidator validator;

    public CommandExecutor(ServerCommunicator serverCommunicator, UIHandler uiHandler) {
        this.serverCommunicator = serverCommunicator;
        this.uiHandler = uiHandler;
        this.validator = new UserDataValidator();
    }

    public void showUser(String userLogin) throws ServerCommunicationException {
        UserDTO userDTO = serverCommunicator.getUserDataFromServer(userLogin);
        uiHandler.printUserData(userDTO);
    }

    public void addUser(String userLogin) throws ServerCommunicationException {
        UserDTO userDTO = getNewUserInfo(userLogin);
        serverCommunicator.userAdd(userDTO);
    }

    public void addUser(String userLogin, String fileName) throws CommandExecutionException, ServerCommunicationException {
        UserDTO userDTO = null;
        try {
            userDTO = getNewUserInfo(userLogin, fileName);
            serverCommunicator.userAdd(userDTO);
        } catch (IOException e) {
            throw new CommandExecutionException("Error when reading user data file. " + e.getMessage(), e);
        }
    }

    private UserDTO getNewUserInfo(String userLogin, String fileName) throws CommandExecutionException, IOException {
        UserDTO user = new UserDTO();
        List<String[]> userData = FileReader.readUserData(fileName);

        List<ValidationResult> validationResults =
                userData
                        .stream()
                        .map(l -> validator.validateUserDataLine(l))
                        .filter(l -> l.isNotCorrect())
                        .collect(Collectors.toList());

        return user;
    }

    private UserDTO getNewUserInfo(String userLogin) {
        return new UserDTO();
    }

}
