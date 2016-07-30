package com.jrd.itmas_client.interpreter;

import com.jrd.itmas_client.infrastructure.exceptions.CommandExecutionException;
import com.jrd.itmas_client.infrastructure.exceptions.ServerCommunicationException;
import com.jrd.itmas_client.infrastructure.utils.Literals;
import com.jrd.itmas_client.infrastructure.utils.UserDataFileReader;
import com.jrd.itmas_client.infrastructure.validation.UserDataValidator;
import com.jrd.itmas_client.infrastructure.validation.ValidationResult;
import com.jrd.itmas_client.servercom.ServerCommunicator;
import com.jrd.itmas_client.servercom.dto.UserDTO;
import com.jrd.itmas_client.ui.UIHandler;
import com.jrd.itmas_server.api.rest.dto.TaskDTO;

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

    private final Literals.Exceptions.CommandExecutionExceptions commandExceptions
            = Literals.get().getExceptions().getCommandExecutionExceptions();

    public CommandExecutor(ServerCommunicator serverCommunicator, UIHandler uiHandler) {
        this.serverCommunicator = serverCommunicator;
        this.uiHandler = uiHandler;
        this.validator = new UserDataValidator();
    }

    public UserDTO showUser(String userLogin) throws ServerCommunicationException {
        UserDTO userDTO = serverCommunicator.getUserDataFromServer(userLogin);
        uiHandler.printUserData(userDTO);
        return userDTO;
    }

    public UserDTO addUser(String userLogin) throws ServerCommunicationException {
        UserDTO userDTO = getNewUserInfo(userLogin);
        return serverCommunicator.userAdd(userDTO);
    }

    public UserDTO addUser(String userLogin, String fileName) throws CommandExecutionException, ServerCommunicationException {
        Optional<UserDTO> userDTO;
        try {
            userDTO = getNewUserInfo(userLogin, fileName);
            return serverCommunicator.userAdd(userDTO.get());
        } catch (IOException e) {
            throw new CommandExecutionException(commandExceptions.getErrorReadingFile() + e.getMessage(), e);
        } catch (CommandExecutionException ex) {
            throw ex;
        }
    }

    public List<TaskDTO> tasksShow(String userLogin) throws ServerCommunicationException {
        return serverCommunicator.allUserTasks(userLogin);
    }

    public TaskDTO createTask(TaskDTO task) {
        //TODO
        return task;
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
        userDTO.setPassword(mUserData.get(UserDataValidator.password));
        return Optional.of(userDTO);
    }

}
