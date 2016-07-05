package com.jrd.itmas_client.interpreter;

import com.jrd.itmas_client.infrastructure.exceptions.CommandExecutionException;
import com.jrd.itmas_client.infrastructure.exceptions.ServerCommunicationException;
import com.jrd.itmas_client.infrastructure.utils.UserDataFileReader;
import com.jrd.itmas_client.infrastructure.validation.UserDataValidator;
import com.jrd.itmas_client.infrastructure.validation.ValidationResult;
import com.jrd.itmas_client.servercom.ServerCommunicator;
import com.jrd.itmas_client.servercom.dto.UserDTO;
import com.jrd.itmas_client.ui.UIHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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
        Optional<UserDTO> userDTO;
        try {
            userDTO = getNewUserInfo(userLogin, fileName);
            if (userDTO.isPresent()) {
                serverCommunicator.userAdd(userDTO.get());
            }
        } catch (IOException e) {
            throw new CommandExecutionException("Error when reading user data file. " + e.getMessage(), e);
        }
    }

    private Optional<UserDTO> getNewUserInfo(String userLogin, String fileName) throws CommandExecutionException, IOException {
        List<String[]> userData = UserDataFileReader.readUserData(fileName);

        List<ValidationResult> validationErrors =
                userData
                        .stream()
                        .map(l -> validator.validateUserDataLine(l))
                        .filter(l -> l.isNotCorrect())
                        .collect(Collectors.toList());

        if (validationErrors.size() > 0) {
            uiHandler.showValidationErrors(validationErrors);
            return Optional.empty();
        } else {
            return getUserInfoFromUserData(userData);
        }
    }

    private UserDTO getNewUserInfo(String userLogin) {
        return new UserDTO();
    }

    private Optional<UserDTO> getUserInfoFromUserData(List<String[]> userData) {
        Map<String, String> mUserData = new HashMap<>();
        userData.stream().forEach(v -> mUserData.put(v[0], v[1]));

        UserDTO userDTO = new UserDTO();
        userDTO.setFirstName(mUserData.get(UserDataValidator.firstName));
        userDTO.setLastName(mUserData.get(UserDataValidator.lastName));
        userDTO.setLogin(mUserData.get(UserDataValidator.login));
        userDTO.setEmail(mUserData.get(UserDataValidator.email));
        userDTO.addAuthority(mUserData.get(UserDataValidator.role));
        return Optional.of(userDTO);
    }

}
