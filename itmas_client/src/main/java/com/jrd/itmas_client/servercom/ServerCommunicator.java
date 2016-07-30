package com.jrd.itmas_client.servercom;

import com.jrd.itmas_client.infrastructure.exceptions.ServerCommunicationException;
import com.jrd.itmas_client.servercom.dto.UserDTO;
import com.jrd.itmas_server.api.rest.dto.TaskDTO;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by jakub on 12.06.16.
 */
public interface ServerCommunicator {
    // USERS:
    UserDTO getUserDataFromServer(String userLogin) throws ServerCommunicationException;
    UserDTO userAdd(UserDTO userDTO) throws ServerCommunicationException;

    void deleteUser(String userLogin) throws ServerCommunicationException;

    // TASKS:

    List<TaskDTO> allUserTasks(String userLogin) throws ServerCommunicationException;
}
